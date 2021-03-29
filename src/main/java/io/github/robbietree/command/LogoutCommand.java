package io.github.robbietree.command;

import io.github.robbietree.infra.AuthStorage;
import picocli.CommandLine.Command;

import java.util.Optional;

@Command(name = "logout", description = "logout from system", mixinStandardHelpOptions = true)
public class LogoutCommand implements Runnable {
    @Override
    public void run() {
        final Optional<String> currentUserOpt = AuthStorage.currentUser();
        currentUserOpt.ifPresent(AuthStorage::removeUser);
        System.out.println("Logout success!");
    }
}
