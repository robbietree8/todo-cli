package io.github.robbietree.infra;

import io.github.robbietree.domain.DbConnection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class DbMigration {
    private static final Logger logger = LoggerFactory.getLogger(DbMigration.class);

    private static final String urlFormat = "jdbc:mysql://%s:%d/%s";

    public static void migrate() {
        DbConnection connection = ConnectionInfoLoader.load();

        String url = String.format(urlFormat, connection.getHost(), connection.getPort(), connection.getDatabase());
        Flyway flyway = Flyway.configure().dataSource(url, connection.getUsername(), connection.getPassword()).load();
        flyway.migrate();
    }

    public static boolean isDbReady() {
        QueryRunner queryRunner = ConnectionInfoLoader.queryRunner();

        ResultSetHandler<DualWrapper> h = new BeanHandler<>(DualWrapper.class);
        try {
            final DualWrapper rtn = queryRunner.query("select 1 as count from dual", h);
            return rtn != null && rtn.getCount() == 1;
        } catch (SQLException throwable) {
            logger.error("query failed", throwable);
            return false;
        }
    }
}
