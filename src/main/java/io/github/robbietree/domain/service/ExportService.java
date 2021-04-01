package io.github.robbietree.domain.service;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class ExportService {
    @Inject
    ItemRepository itemRepository;

    public Collection<Item> export(final String username) {
        return itemRepository.listAllByUser(username);
    }
}
