package io.github.robbietree.command;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.SessionRepository;
import io.github.robbietree.domain.service.ListService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.inject.Inject;
import java.util.Collection;

@Command(name = "list", description = "List todo items.", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {
    @Option(names = {"-a", "--all"}, description = "all items.")
    private boolean all;

    @Inject
    private SessionRepository sessionRepository;

    @Inject
    private ListService listService;

    @Override
    public void run() {
        String currentUser = sessionRepository.getCurrentUser();

        Collection<Item> items = listService.list(currentUser, all);

        if(items.isEmpty()) {
            System.out.println("No items found");
            return;
        }

        items.forEach(this::print);

        final long totalCount = items.size();
        final long doneCount = items.stream().filter(Item::isDone).count();

        System.out.printf("\n\nTotal: %d items, %d item done\n", totalCount, doneCount);
    }

    private void print(Item item) {
        if(item.isDone()) {
            System.out.printf("%d. [DONE] %s\n", item.getItemIndex(), item.getContent());
        }else {
            System.out.printf("%d. %s\n", item.getItemIndex(), item.getContent());
        }
    }
}
