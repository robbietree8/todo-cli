package io.github.robbietree.domain;

public interface AuthRepository {

    boolean auth(final String username, final String password);
}
