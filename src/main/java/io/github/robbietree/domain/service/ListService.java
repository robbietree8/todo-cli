package io.github.robbietree.domain.service;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class ListService {
    @Inject
    private ItemRepository itemRepository;

    public Collection<Item> list(final String username, final boolean all) {
        Collection<Item> items;
        if(all) {
            items = itemRepository.listAllByUser(username);
        }else {
            items = itemRepository.listUnDone(username);
        }

        return items;
    }
}
