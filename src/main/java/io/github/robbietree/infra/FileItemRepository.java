package io.github.robbietree.infra;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;
import io.github.robbietree.domain.ItemStatusEnum;
import io.micronaut.context.annotation.Primary;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * index \t status \t content
 */
@Primary
@Singleton
public class FileItemRepository implements ItemRepository {
    @Override
    public Long save(Item item) {
        final String line = String.format("%d\t%s\t%s", item.getIndex(), item.getStatus(), item.getContent());
        TodoStorage.write(line);
        return item.getIndex();
    }

    @Override
    public Collection<Item> listAll() {
        final List<String> lines = TodoStorage.read();
        return lines.stream()
                    .map(line -> line.split("\t"))
                    .map(e -> new Item(Long.valueOf(e[0]), ItemStatusEnum.valueOf(e[1]), e[2]))
                    .collect(Collectors.toList());
    }

    @Override
    public Long nextIndex() {
        return listAll()
                .stream()
                .max(Comparator.comparing(Item::getIndex))
                .map(item -> item.getIndex() + 1)
                .orElse(1L);
    }

    @Override
    public Optional<Item> findByIndex(Long index) {
        return listAll()
                .stream()
                .filter(i -> Objects.equals(index, i.getIndex()))
                .findFirst();
    }

    @Override
    public void update(Item item) {
        Collection<Item> newItems = new ArrayList<>();
        for (Item ele : listAll()) {
            if(Objects.equals(item.getIndex(), ele.getIndex())) {
                newItems.add(item);
            }else {
                newItems.add(ele);
            }
        }

        TodoStorage.truncate();
        newItems.forEach(this::save);
    }

    @Override
    public Collection<Item> listUnDone() {
        return listAll()
                .stream()
                .filter(Item::notDone)
                .collect(Collectors.toList());
    }
}
