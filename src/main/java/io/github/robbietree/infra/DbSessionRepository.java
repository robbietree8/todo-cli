package io.github.robbietree.infra;

import io.github.robbietree.domain.SessionRepository;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.Optional;

public class DbSessionRepository implements SessionRepository {
    @Override
    public void createSession(String username) {
        QueryRunner queryRunner = JdbcUtils.queryRunner();

        try {
            queryRunner.execute("insert into session(username, create_time) values(?, now())", username);
        } catch (SQLException throwable) {
            throw new IllegalStateException("create session failed " + username);
        }
    }

    @Override
    public void removeUser(String username) {
        QueryRunner queryRunner = JdbcUtils.queryRunner();

        try {
            queryRunner.execute("delete from session where username = ?", username);
        } catch (SQLException throwable) {
            throw new IllegalStateException("remove user failed " + username);
        }
    }

    @Override
    public Optional<String> currentUser() {
        QueryRunner queryRunner = JdbcUtils.queryRunner();

        ResultSetHandler<UsernameWrapper> h = new BeanHandler<>(UsernameWrapper.class);

        try{
            final UsernameWrapper currentUser = queryRunner.query("select username from session order by create_time desc limit 1", h);
            return Optional.ofNullable(currentUser).map(UsernameWrapper::getUsername);
        } catch (SQLException throwable) {
            throw new IllegalStateException("find by index failed " + throwable.getMessage());
        }
    }
}
