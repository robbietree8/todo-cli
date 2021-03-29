package io.github.robbietree.infra;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TodoConfigStorage {
    static final Path LOCATION = Paths.get(String.format(
            "%s/.todo-cli/.todo-config",
            System.getenv("HOME"))
    );

    public static void init() {
        FileUtils.createFile(LOCATION);
    }

    public static List<String> read() {
        return FileUtils.lines(LOCATION);
    }

}
