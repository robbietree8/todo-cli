package io.github.robbietree.domain;

import java.util.Optional;

public interface SessionRepository {
    void createSession(String username);

    void removeUser(String username);

    Optional<String> currentUser();

    default String getCurrentUser() {
        return currentUser().orElseThrow(IllegalStateException::new);
    }
}
