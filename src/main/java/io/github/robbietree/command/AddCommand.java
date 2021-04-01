package io.github.robbietree.command;

import io.github.robbietree.domain.SessionRepository;
import io.github.robbietree.domain.service.AddService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import javax.inject.Inject;

@Command(name = "add", description = "Add todo item.", mixinStandardHelpOptions = true)
public class AddCommand implements Runnable {
    @Parameters(description = "Item content.")
    String content = "";

    @Inject
    SessionRepository sessionRepository;

    @Inject
    AddService addService;

    @Override
    public void run() {
        String currentUser = sessionRepository.getCurrentUser();

        addService.add(currentUser, content);

        Long nextIndex = addService.add(currentUser, content);

        System.out.printf("\n%d. %s\n", nextIndex, content);
        System.out.printf("\nItem %d added\n", nextIndex);
    }
}
