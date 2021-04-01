package io.github.robbietree.command;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;
import io.github.robbietree.infra.AuthStorage;
import io.github.robbietree.utils.FileUtils;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Path;

@Command(name = "import", description = "import todo items", mixinStandardHelpOptions = true)
public class ImportCommand implements Runnable {
    @Option(names = {"-f", "--file"}, description = "file to import")
    Path path;

    @Inject
    ItemRepository itemRepository;

    @Override
    public void run() {
        final String currentUser = AuthStorage.getCurrentUser();

        if(!Files.exists(path)) {
            System.out.println("provided file does not exist");
            return;
        }

        for (String line : FileUtils.lines(path)) {
            String[] ele = line.split("\t");

            Item item = Item.create(itemRepository.nextIndex(currentUser), currentUser, ele[2]);
            itemRepository.save(item);
        }

        System.out.println("Import success!");
    }
}
