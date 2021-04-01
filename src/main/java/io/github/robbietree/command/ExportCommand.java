package io.github.robbietree.command;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.SessionRepository;
import io.github.robbietree.domain.service.ExportService;
import picocli.CommandLine.Command;

import javax.inject.Inject;
import java.util.Collection;

@Command(name = "export", description = "export todo items", mixinStandardHelpOptions = true)
public class ExportCommand implements Runnable {
    @Inject
    SessionRepository sessionRepository;

    @Inject
    ExportService exportService;

    @Override
    public void run() {
        final String currentUser = sessionRepository.getCurrentUser();

        final Collection<Item> items = exportService.export(currentUser);

        items.forEach(this::printItem);
    }

    private void printItem(Item item) {
        System.out.printf("%d\t%s\t%s\n", item.getItemIndex(), item.getStatus().name(), item.getContent());
    }
}
