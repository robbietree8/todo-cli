package io.github.robbietree.command;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import javax.inject.Inject;
import java.util.Optional;

@Command(name = "done", description = "Complete todo item", mixinStandardHelpOptions = true)
public class DoneCommand implements Runnable {
    @Parameters(description = "item index.")
    Long index;

    @Inject
    ItemRepository itemRepository;

    @Override
    public void run() {
        Optional<Item> itemOpt = itemRepository.findByIndex(index);

        if(!itemOpt.isPresent()) {
            System.out.println("No item found");
            return;
        }

        Item item = itemOpt.get();
        item.markAsDone();
        itemRepository.update(item);

        System.out.printf("Item %d done.",item.getIndex());
    }
}
