package io.github.robbietree.infra;

import io.github.robbietree.domain.AuthRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Objects;

@Singleton
public class FileAuthRepository implements AuthRepository {

    @Override
    public boolean auth(String username, String password) {
        final List<String> configs = TodoConfigStorage.read();
        return configs
                .stream()
                .map(x -> x.split("="))
                .anyMatch(e -> Objects.equals(username, e[0]) && Objects.equals(password, e[1]));
    }
}
