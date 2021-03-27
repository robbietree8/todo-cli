package io.github.robbietree.domain;

import java.util.Collection;
import java.util.Optional;

public interface ItemRepository {
    Long save(Item item);

    Collection<Item> listAll();

    Long nextIndex();

    Optional<Item> findByIndex(Long index);

    void update(Item item);

    Collection<Item> listUnDone();
}
