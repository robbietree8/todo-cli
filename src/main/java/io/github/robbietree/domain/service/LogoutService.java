package io.github.robbietree.domain.service;

import io.github.robbietree.domain.SessionRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LogoutService {
    @Inject
    SessionRepository sessionRepository;

    public void logout(final String username) {
        sessionRepository.removeUser(username);
    }
}
