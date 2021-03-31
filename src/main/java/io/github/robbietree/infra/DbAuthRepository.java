package io.github.robbietree.infra;

import io.github.robbietree.domain.AuthRepository;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.Objects;

public class DbAuthRepository implements AuthRepository {

    @Override
    public boolean auth(String username, String password) {
        QueryRunner queryRunner = ConnectionInfoLoader.queryRunner();

        ResultSetHandler<String> h = new BeanHandler<>(String.class);
        try {
            final String rtn = queryRunner.query("select username from auth where username = ? and password = ?",
                    h, username, password);
            return Objects.equals(username, rtn);
        } catch (SQLException throwable) {
            throw new IllegalStateException("auth failed " + throwable.getMessage());
        }
    }

    public void add(String username, String password) {
        QueryRunner queryRunner = ConnectionInfoLoader.queryRunner();

        try {
            queryRunner.execute("insert into auth(username, password) values(?, ?)",
                    username, password);
        } catch (SQLException throwable) {
            throw new IllegalStateException("add auth info failed " + username);
        }
    }

}
