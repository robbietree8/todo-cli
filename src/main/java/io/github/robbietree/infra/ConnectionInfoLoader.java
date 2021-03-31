package io.github.robbietree.infra;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.robbietree.domain.DbConnection;
import org.apache.commons.dbutils.QueryRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionInfoLoader {
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
        DbConnection connectionInfo = ConnectionInfoLoader.load();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(connectionInfo.jdbcUrl());
        config.setUsername(connectionInfo.getUsername());
        config.setPassword(connectionInfo.getPassword());

        HikariDataSource dataSource = new HikariDataSource(config);

        return new QueryRunner(dataSource);
    }

}
