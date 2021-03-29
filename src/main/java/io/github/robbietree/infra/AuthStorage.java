package io.github.robbietree.infra;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public static void set(String username, boolean success) {
        String line = String.format("%s=%s", username, Boolean.toString(success));
        FileUtils.writeLine(LOCATION, line);
    }

    public static void removeUser(String username) {
        final List<String> newLines =
                read().stream()
                      .filter(x -> !x.startsWith(username + "="))
                      .collect(Collectors.toList());

        FileUtils.truncate(LOCATION);

        FileUtils.writeLines(LOCATION, newLines);
    }

    public static Optional<String> currentUser() {
        return read().stream()
                     .filter(x -> x.endsWith("=true"))
                     .map(x -> x.split("=")[0])
                     .findFirst();
    }

    public static String getCurrentUser() {
        return currentUser().orElseThrow(IllegalStateException::new);
    }
}
