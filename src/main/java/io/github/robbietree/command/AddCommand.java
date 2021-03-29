package io.github.robbietree.command;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import javax.inject.Inject;

@Command(name = "add", description = "Add todo items.", mixinStandardHelpOptions = true)
public class AddCommand implements Runnable {
    @Parameters(description = "Item content.")
    String content = "";

    @Inject
    ItemRepository itemRepository;

    @Override
    public void run() {
        Long nextIndex = itemRepository.nextIndex();

        Item item = Item.create(nextIndex, content);
        itemRepository.save(item);

        System.out.printf("\n%d. %s\n", nextIndex, content);
        System.out.printf("\nItem %d added\n", nextIndex);
    }
}
