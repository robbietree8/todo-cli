package io.github.robbietree.command;

import io.github.robbietree.domain.SessionRepository;
import io.github.robbietree.domain.service.DoneService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import javax.inject.Inject;

@Command(name = "done", description = "Complete one todo item", mixinStandardHelpOptions = true)
public class DoneCommand implements Runnable {
    @Parameters(description = "item index.")
    Long index;

    @Inject
    SessionRepository sessionRepository;

    @Inject
    DoneService doneService;

    @Override
    public void run() {
        String currentUser = sessionRepository.getCurrentUser();

        doneService.done(currentUser, index);

        System.out.printf("Item %d done.\n", index);
    }
}
