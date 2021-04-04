package io.github.robbietree.command;

import io.github.robbietree.domain.SessionRepository;
import io.github.robbietree.domain.service.AddService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

import java.io.PrintWriter;

@Command(name = "add", description = "Add todo item.", mixinStandardHelpOptions = true)
public class AddCommand implements Runnable {
    @Spec
    CommandSpec spec;

    @Parameters(description = "Item content.")
    String content = "";

    private final SessionRepository sessionRepository;
    private final AddService addService;

    public AddCommand(SessionRepository sessionRepository, AddService addService) {
        this.sessionRepository = sessionRepository;
        this.addService = addService;
    }

    @Override
    public void run() {
        String currentUser = sessionRepository.getCurrentUser();

        Long nextIndex = addService.add(currentUser, content);

        getOut().printf("\n%d. %s\n", nextIndex, content);
        getOut().printf("\nItem %d added\n", nextIndex);
    }

    private PrintWriter getOut() {
        return spec.commandLine().getOut();
    }
}
