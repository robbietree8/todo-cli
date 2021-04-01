package io.github.robbietree.infra;

import io.github.robbietree.domain.AuthRepository;
import io.github.robbietree.domain.Authentication;
import io.github.robbietree.utils.FileUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileAuthRepository implements AuthRepository {
    static final Path LOCATION = Paths.get(String.format(
            "%s/.todo-cli/.todo-config",
            System.getenv("HOME"))
    );

    public FileAuthRepository() {
        FileUtils.createFile(LOCATION);
    }

    @Override
    public boolean auth(String username, String password) {
        final List<String> configs = FileUtils.lines(LOCATION);
        return configs
                .stream()
                .map(x -> x.split("="))
                .anyMatch(e -> Objects.equals(username, e[0]) && Objects.equals(password, e[1]));
    }

    public Collection<Authentication> listAuth() {
        final List<String> configs = FileUtils.lines(LOCATION);
        return configs
                .stream()
                .map(x -> x.split("="))
                .map(x -> new Authentication(x[0], x[1]))
                .collect(Collectors.toList());
    }
}
