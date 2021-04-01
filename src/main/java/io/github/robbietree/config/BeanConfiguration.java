package io.github.robbietree.config;

import io.github.robbietree.domain.AuthRepository;
import io.github.robbietree.domain.ItemRepository;
import io.github.robbietree.infra.DbAuthRepository;
import io.github.robbietree.infra.DbItemRepository;
import io.github.robbietree.infra.DbMigration;
import io.github.robbietree.infra.FileAuthRepository;
import io.github.robbietree.infra.FileItemRepository;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class BeanConfiguration {
    @Singleton
    public AuthRepository authRepository() {
        if(DbMigration.isDbReady()) {
            return new DbAuthRepository();
        }else {
            return new FileAuthRepository();
        }
    }

    @Singleton
    public ItemRepository itemRepository() {
        if(DbMigration.isDbReady()) {
            return new DbItemRepository();
        }else {
            return new FileItemRepository();
        }
    }
}
