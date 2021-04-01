package io.github.robbietree.domain.service;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;
import io.github.robbietree.utils.FileUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.file.Files;
import java.nio.file.Path;

@Singleton
public class ImportService {
    @Inject
    ItemRepository itemRepository;

    public void importItems(final String username, final Path path) {
        if(!Files.exists(path)) {
            System.out.println("provided file does not exist");
            return;
        }

        for (String line : FileUtils.lines(path)) {
            String[] ele = line.split("\t");

            Item item = Item.create(itemRepository.nextIndex(username), username, ele[2]);
            itemRepository.save(item);
        }
    }
}
