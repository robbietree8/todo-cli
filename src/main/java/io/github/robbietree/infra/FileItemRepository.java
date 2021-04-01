package io.github.robbietree.infra;

import io.github.robbietree.domain.Item;
import io.github.robbietree.domain.ItemRepository;
import io.github.robbietree.domain.ItemStatusEnum;
import io.github.robbietree.utils.FileUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
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
    static final Path LOCATION = Paths.get(String.format(
            "%s/.todo-cli/todos.csv",
            System.getenv("HOME"))
    );

    public FileItemRepository() {
        FileUtils.createFile(LOCATION);
    }

    @Override
    public Long save(Item item) {
        final String line = String.format("%d\t%s\t%s\t%s", item.getItemIndex(), item.getStatus(), item.getUsername(), item.getContent());
        FileUtils.appendLine(LOCATION, line);
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

        FileUtils.truncate(LOCATION);
        newItems.forEach(this::save);
    }


    @Override
    public Collection<Item> listAll() {
        return FileUtils.lines(LOCATION).stream()
                          .map(line -> line.split("\t"))
                          .map(e -> new Item(Long.valueOf(e[0]), ItemStatusEnum.valueOf(e[1]), e[2], e[3]))
                          .collect(Collectors.toList());
    }
}
