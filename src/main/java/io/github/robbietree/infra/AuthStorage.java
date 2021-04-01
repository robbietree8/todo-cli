package io.github.robbietree.infra;

import io.github.robbietree.utils.FileUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class AuthStorage {
    static final Path LOCATION = Paths.get(String.format(
            "%s/.todo-cli/authentication",
            System.getenv("HOME"))
    );

    public static void init() {
        FileUtils.createFile(LOCATION);
    }

    public static List<String> read() {
        return FileUtils.lines(LOCATION);
    }

    public static void write(String line) {
        FileUtils.appendLine(LOCATION, line);
    }

    public static void truncate() {
        FileUtils.truncate(LOCATION);
    }

    public static void overwrite(String line) {
        FileUtils.overwrite(LOCATION, line);
    }

    public static void write(List<String> lines) {
        FileUtils.appendLines(LOCATION, lines);
    }

}
