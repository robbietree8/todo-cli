package io.github.robbietree.command;

import io.github.robbietree.domain.SessionRepository;
import io.github.robbietree.domain.service.ImportService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.inject.Inject;
import java.nio.file.Path;

@Command(name = "import", description = "import todo items", mixinStandardHelpOptions = true)
public class ImportCommand implements Runnable {
    @Option(names = {"-f", "--file"}, description = "file to import")
    Path path;

    @Inject
    SessionRepository sessionRepository;

    @Inject
    ImportService importService;

    @Override
    public void run() {
        final String currentUser = sessionRepository.getCurrentUser();

        importService.importItems(currentUser, path);

        System.out.println("Import success!");
    }
}
