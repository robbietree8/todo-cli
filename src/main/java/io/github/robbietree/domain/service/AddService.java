package io.github.robbietree.domain.service;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;

import javax.inject.Singleton;

@Singleton
public class AddService {

    private final ItemRepository itemRepository;

    public AddService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Long add(final String username, final String content) {
        Long nextIndex = itemRepository.nextIndex(username);

        Item item = Item.create(nextIndex, username, content);
        itemRepository.save(item);

        return nextIndex;
    }
}
