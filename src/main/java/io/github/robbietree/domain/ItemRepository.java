package io.github.robbietree.domain;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ItemRepository {
    Long save(Item item);

    Collection<Item> listAllByUser(String username);

    default Long nextIndex(String username) {
        return listAllByUser(username)
                .stream()
                .max(Comparator.comparing(Item::getItemIndex))
                .map(item -> item.getItemIndex() + 1)
                .orElse(1L);
    }

    Optional<Item> findByIndex(String username, Long index);

    void update(Item item);

    default Collection<Item> listUnDone(String username) {
        return listAllByUser(username)
                .stream()
                .filter(Item::notDone)
                .collect(Collectors.toList());
    }

    Collection<Item> listAll();
}
