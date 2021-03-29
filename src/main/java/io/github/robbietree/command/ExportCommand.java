package io.github.robbietree.command;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;
import io.github.robbietree.infra.AuthStorage;
import picocli.CommandLine.Command;

import javax.inject.Inject;
import java.util.Collection;

@Command(name = "export", description = "export todo items", mixinStandardHelpOptions = true)
public class ExportCommand implements Runnable {
    @Inject
    ItemRepository itemRepository;

    @Override
    public void run() {
        final String currentUser = AuthStorage.getCurrentUser();

        final Collection<Item> items = itemRepository.listAllByUser(currentUser);
        for (Item item : items) {
            printItem(item);
        }
    }

    private void printItem(Item item) {
        System.out.printf("%d\t%s\t%s\n", item.getIndex(), item.getStatus().name(), item.getContent());
    }
}
