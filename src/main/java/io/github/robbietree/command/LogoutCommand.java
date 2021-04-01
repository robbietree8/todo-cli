package io.github.robbietree.command;

import io.github.robbietree.domain.SessionRepository;
import io.github.robbietree.domain.service.LogoutService;
import picocli.CommandLine.Command;

import javax.inject.Inject;
import java.util.Optional;

@Command(name = "logout", description = "logout from system", mixinStandardHelpOptions = true)
public class LogoutCommand implements Runnable {
    @Inject
    SessionRepository sessionRepository;
    @Inject
    LogoutService logoutService;

    @Override
    public void run() {
        final Optional<String> currentUserOpt = sessionRepository.currentUser();
        currentUserOpt.ifPresent(u -> logoutService.logout(u));
        System.out.println("Logout success!");
    }
}
