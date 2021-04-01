package io.github.robbietree.infra;

import io.github.robbietree.domain.SessionRepository;
import io.github.robbietree.utils.FileUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileSessionRepository implements SessionRepository {
    static final Path LOCATION = Paths.get(String.format(
            "%s/.todo-cli/authentication",
            System.getenv("HOME"))
    );

    public FileSessionRepository() {
        FileUtils.createFile(LOCATION);
    }

    @Override
    public void createSession(String username) {
        FileUtils.overwrite(LOCATION, username);
    }

    @Override
    public void removeUser(String username) {
        final List<String> newLines =
                FileUtils.lines(LOCATION).stream()
                      .filter(x -> !x.startsWith(username + "="))
                      .collect(Collectors.toList());

        FileUtils.truncate(LOCATION);

        FileUtils.appendLines(LOCATION, newLines);
    }

    @Override
    public Optional<String> currentUser() {
        return FileUtils.lines(LOCATION).stream()
                     .filter(x -> x.endsWith("=true"))
                     .map(x -> x.split("=")[0])
                     .findFirst();
    }
}
