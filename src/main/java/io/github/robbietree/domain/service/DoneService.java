package io.github.robbietree.domain.service;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class DoneService {
    @Inject
    ItemRepository itemRepository;

    public void done(final String username, final Long index) {
        Optional<Item> itemOpt = itemRepository.findByIndex(username, index);

        if(!itemOpt.isPresent()) {
            System.out.println("No item found");
            return;
        }

        Item item = itemOpt.get();
        item.markAsDone();
        itemRepository.update(item);
    }
}
