package io.github.robbietree.command;

import io.github.robbietree.domain.AuthRepository;
import io.github.robbietree.domain.SessionRepository;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.inject.Inject;

@Command(name = "login", description = "login to system", mixinStandardHelpOptions = true)
public class LoginCommand implements Runnable {
    @Option(names = {"-u"}, description = "username")
    String username = "";

    @Option(names = {"-p"}, description = "password", interactive = true, arity = "0..1")
    String password = "";

    @Inject
    AuthRepository authRepository;
    @Inject
    SessionRepository sessionRepository;

    @Override
    public void run() {
        if(username.isBlank()) {
            System.out.println("username is necessary");
            return;
        }

        boolean success = authRepository.auth(username, password);
        if(success) {
            sessionRepository.createSession(username);
            System.out.println("Login success!");
        }else {
            System.out.println("Login failed!");
        }
    }
}
