package io.github.robbietree.infra;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TodoStorage {
    static final Path LOCATION = Paths.get(String.format(
            "%s/.todo-cli/todos.csv",
            System.getenv("HOME"))
    );

    public static void init() {
        try {
            Files.createDirectories(LOCATION.getParent());
            if(!Files.exists(LOCATION)) {
                Files.createFile(LOCATION);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can not create file in " + LOCATION);
        }
    }

    public static List<String> read() {
        try {
            return Files.readAllLines(LOCATION);
        } catch (IOException e) {
            throw new IllegalStateException("Can not read file " + LOCATION);
        }
    }

    public static void write(String line) {
        try {
            Files.write(LOCATION, (line + System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new IllegalStateException("Can not write file " + LOCATION);
        }
    }

    public static void truncate() {
        try {
            Files.write(LOCATION, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("Can not truncate file " + LOCATION);
        }
    }
}
