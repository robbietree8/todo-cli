package io.github.robbietree.infra;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.robbietree.domain.DbConnection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

    static final String LOCATION = String.format(
            "%s/.todo-cli/init.config",
            System.getenv("HOME"));

    public static DbConnection load() {
        Properties properties = new Properties();
        try(final FileInputStream stream = new FileInputStream(LOCATION)) {
            properties.load(stream);
        } catch (IOException e) {
            throw new IllegalStateException("can not read " + LOCATION);
        }

        DbConnection connection = new DbConnection();
        connection.setHost(properties.getProperty("host"));
        connection.setPort(Integer.parseInt(properties.getProperty("port", "3306")));
        connection.setUsername(properties.getProperty("username"));
        connection.setPassword(properties.getProperty("password"));
        connection.setDatabase(properties.getProperty("database"));
        return connection;
    }

    public static QueryRunner queryRunner() {
        DbConnection connectionInfo = JdbcUtils.load();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(connectionInfo.jdbcUrl());
        config.setUsername(connectionInfo.getUsername());
        config.setPassword(connectionInfo.getPassword());

        HikariDataSource dataSource = new HikariDataSource(config);

        return new QueryRunner(dataSource);
    }

    public static void migrate() {
        DbConnection connection = load();
        Flyway flyway = Flyway.configure().dataSource(connection.jdbcUrl(), connection.getUsername(), connection.getPassword()).load();
        flyway.migrate();
    }

    public static boolean isDbReady() {
        QueryRunner queryRunner = queryRunner();

        ResultSetHandler<DualWrapper> h = new BeanHandler<>(DualWrapper.class);
        try {
            final DualWrapper rtn = queryRunner.query("select 1 as count", h);
            return rtn != null && rtn.getCount() == 1;
        } catch (SQLException throwable) {
            logger.error("query failed", throwable);
            return false;
        }
    }
}
