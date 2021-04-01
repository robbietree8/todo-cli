package io.github.robbietree.domain.service;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AddService {
    @Inject
    ItemRepository itemRepository;

    public Long add(final String username, final String content) {
        Long nextIndex = itemRepository.nextIndex(username);

        Item item = Item.create(nextIndex, username, content);
        itemRepository.save(item);

        return nextIndex;
    }
}
