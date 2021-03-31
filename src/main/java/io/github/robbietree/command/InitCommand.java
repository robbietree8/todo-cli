package io.github.robbietree.command;

import io.github.robbietree.domain.Authentication;
import io.github.robbietree.domain.Item;
import io.github.robbietree.infra.DbAuthRepository;
import io.github.robbietree.infra.DbItemRepository;
import io.github.robbietree.infra.DbMigration;
import io.github.robbietree.infra.FileAuthRepository;
import io.github.robbietree.infra.FileItemRepository;
import picocli.CommandLine.Command;

import java.util.Collection;

@Command(name = "init", description = "init database", mixinStandardHelpOptions = true)
public class InitCommand implements Runnable {
    @Override
    public void run() {
        if(DbMigration.isDbReady()) {
            DbMigration.migrate();

            migrateAuth();
            migrateItems();

            System.out.println("init done");
        }else {
            System.out.println("db is not configed properly");
        }

    }

    private void migrateAuth() {
        FileAuthRepository far = new FileAuthRepository();
        DbAuthRepository dar = new DbAuthRepository();

        Collection<Authentication> authentications = far.listAuth();
        for (Authentication authentication : authentications) {
            dar.add(authentication.getUsername(), authentication.getPassword());
        }

        System.out.println("migrate auth success");
    }

    private void migrateItems() {
        FileItemRepository fir = new FileItemRepository();
        DbItemRepository dir = new DbItemRepository();

        final Collection<Item> items = fir.listAll();
        for (Item item : items) {
            dir.save(item);
        }

        System.out.println("migrate items success");
    }
}
