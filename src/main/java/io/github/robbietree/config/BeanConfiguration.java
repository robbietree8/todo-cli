package io.github.robbietree.config;

import io.github.robbietree.domain.AuthRepository;
import io.github.robbietree.domain.ItemRepository;
import io.github.robbietree.domain.SessionRepository;
import io.github.robbietree.infra.DbAuthRepository;
import io.github.robbietree.infra.DbItemRepository;
import io.github.robbietree.infra.DbSessionRepository;
import io.github.robbietree.infra.FileAuthRepository;
import io.github.robbietree.infra.FileItemRepository;
import io.github.robbietree.infra.FileSessionRepository;
import io.github.robbietree.infra.JdbcUtils;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class BeanConfiguration {
    @Singleton
    public AuthRepository authRepository() {
        if(JdbcUtils.isDbReady()) {
            return new DbAuthRepository();
        }else {
            return new FileAuthRepository();
        }
    }

    @Singleton
    public ItemRepository itemRepository() {
        if(JdbcUtils.isDbReady()) {
            return new DbItemRepository();
        }else {
            return new FileItemRepository();
        }
    }

    @Singleton
    public SessionRepository sessionRepository() {
        if(JdbcUtils.isDbReady()) {
            return new DbSessionRepository();
        }else {
            return new FileSessionRepository();
        }
    }
}
