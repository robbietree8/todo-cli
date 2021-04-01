package io.github.robbietree.infra;

import io.github.robbietree.domain.SessionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileSessionRepository implements SessionRepository {
    @Override
    public void createSession(String username) {
        AuthStorage.overwrite(username);
    }

    @Override
    public void removeUser(String username) {
        final List<String> newLines =
                AuthStorage.read().stream()
                      .filter(x -> !x.startsWith(username + "="))
                      .collect(Collectors.toList());

        AuthStorage.truncate();

        AuthStorage.write(newLines);
    }

    @Override
    public Optional<String> currentUser() {
        return AuthStorage.read().stream()
                     .filter(x -> x.endsWith("=true"))
                     .map(x -> x.split("=")[0])
                     .findFirst();
    }
}
