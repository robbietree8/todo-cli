package io.github.robbietree.domain.service;

import io.github.robbietree.domain.AuthRepository;
import io.github.robbietree.domain.SessionRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LoginService {
    @Inject
    private AuthRepository authRepository;

    @Inject
    private SessionRepository sessionRepository;


    public boolean login(final String username, final String password) {
        if(username.isBlank()) {
            System.out.println("username is necessary");
            return false;
        }

        boolean success = authRepository.auth(username, password);
        if(success) {
            sessionRepository.createSession(username);
            return true;
        }else {
            return false;
        }
    }
}
