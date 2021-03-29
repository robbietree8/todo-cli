package io.github.robbietree.domain;

import java.util.Collection;
import java.util.Optional;

public interface ItemRepository {
    Long save(Item item);

    Collection<Item> listAllByUser(String username);

    Long nextIndex(String username);

    Optional<Item> findByIndex(String username, Long index);

    void update(Item item);

    Collection<Item> listUnDone(String username);

    Collection<Item> listAll();
}
