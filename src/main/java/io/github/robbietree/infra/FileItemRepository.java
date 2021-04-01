package io.github.robbietree.infra;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;
import io.github.robbietree.domain.ItemStatusEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * index \t status \t username \t content
 */
public class FileItemRepository implements ItemRepository {
    @Override
    public Long save(Item item) {
        final String line = String.format("%d\t%s\t%s\t%s", item.getItemIndex(), item.getStatus(), item.getUsername(), item.getContent());
        TodoStorage.write(line);
        return item.getItemIndex();
    }

    @Override
    public Collection<Item> listAllByUser(String username) {
        return listAll().stream()
                        .filter(i -> Objects.equals(username, i.getUsername()))
                        .collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findByIndex(String username, Long index) {
        return listAllByUser(username)
                .stream()
                .filter(i -> Objects.equals(index, i.getItemIndex()))
                .findFirst();
    }

    @Override
    public void update(Item item) {
        Collection<Item> newItems = new ArrayList<>();
        for (Item ele : listAllByUser(item.getUsername())) {
            if(Objects.equals(item.getItemIndex(), ele.getItemIndex())) {
                newItems.add(item);
            }else {
                newItems.add(ele);
            }
        }

        final List<Item> otherItems =
                listAll().stream()
                         .filter(i -> !Objects.equals(item.getUsername(), i.getUsername()))
                         .collect(Collectors.toList());

        newItems.addAll(otherItems);

        TodoStorage.truncate();
        newItems.forEach(this::save);
    }


    @Override
    public Collection<Item> listAll() {
        return TodoStorage.read().stream()
                          .map(line -> line.split("\t"))
                          .map(e -> new Item(Long.valueOf(e[0]), ItemStatusEnum.valueOf(e[1]), e[2], e[3]))
                          .collect(Collectors.toList());
    }
}
