package io.github.robbietree.infra;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DbItemRepository implements ItemRepository {
    @Override
    public Long save(Item item) {
        QueryRunner queryRunner = ConnectionInfoLoader.queryRunner();
        try {
            queryRunner.execute("insert into items(item_id, status, owner, content)" +
                    "values (?, ? ,?, ?)",
                    item.getItemIndex(), item.getStatus().name(), item.getUsername(), item.getContent());
        } catch (SQLException ex) {
            throw new IllegalStateException("save failed " + ex.getMessage());
        }
        return item.getItemIndex();
    }

    @Override
    public Collection<Item> listAllByUser(String username) {
        QueryRunner queryRunner = ConnectionInfoLoader.queryRunner();
        ResultSetHandler<List<Item>> h = new BeanListHandler<>(Item.class);
        try {
            return queryRunner.query("select item_id as itemIndex, status, owner as username, content " +
                    "from items where owner = ?", h, username);
        } catch (SQLException throwable) {
            throw new IllegalStateException("list failed " + throwable.getMessage());
        }
    }

    @Override
    public Optional<Item> findByIndex(String username, Long index) {
        QueryRunner queryRunner = ConnectionInfoLoader.queryRunner();
        ResultSetHandler<Item> h = new BeanHandler<>(Item.class);

        try{
            final Item item = queryRunner.query("select item_id as itemIndex, status, owner as username, content " +
                    "from items where owner = ? and item_id = ?", h, username, index);
            return Optional.ofNullable(item);
        } catch (SQLException throwable) {
            throw new IllegalStateException("find by index failed " + throwable.getMessage());
        }
    }

    @Override
    public void update(Item item) {
        QueryRunner queryRunner = ConnectionInfoLoader.queryRunner();

        try {
            queryRunner.execute("update items set status = ? where item_id =? and owner = ?",
                    item.getItemIndex(), item.getUsername());
        } catch (SQLException throwable) {
            throw new IllegalStateException("update item failed " + throwable.getMessage());
        }
    }

    @Override
    public Collection<Item> listAll() {
        QueryRunner queryRunner = ConnectionInfoLoader.queryRunner();
        ResultSetHandler<List<Item>> h = new BeanListHandler<>(Item.class);
        try {
            return queryRunner.query("select item_id as itemIndex, status, owner as username, content " +
                    "from items", h);
        } catch (SQLException throwable) {
            throw new IllegalStateException("list all failed " + throwable.getMessage());
        }
    }
}
