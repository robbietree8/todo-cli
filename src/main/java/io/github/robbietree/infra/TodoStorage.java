package io.github.robbietree.infra;

import io.github.robbietree.utils.FileUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TodoStorage {
    static final Path LOCATION = Paths.get(String.format(
            "%s/.todo-cli/todos.csv",
            System.getenv("HOME"))
    );

    public static void init() {
        FileUtils.createFile(LOCATION);
    }

    public static List<String> read() {
        return FileUtils.lines(LOCATION);
    }

    public static void write(String line) {
        FileUtils.writeLine(LOCATION, line);
    }

    public static void truncate() {
        FileUtils.truncate(LOCATION);
    }
}
