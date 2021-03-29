package io.github.robbietree.infra;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileUtils {
    public static void createFile(Path path) {
        try {
            Files.createDirectories(path.getParent());
            if(!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can not create file in " + path);
        }
    }

    public static List<String> lines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new IllegalStateException("Can not read file " + path);
        }
    }

    public static void writeLine(Path path, String line) {
        try {
            Files.write(path, (line + System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new IllegalStateException("Can not write file " + path);
        }
    }

    public static void writeLines(Path path, List<String> lines) {
        lines.forEach(line -> writeLine(path, line));
    }

    public static void truncate(Path path) {
        try {
            Files.write(path, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("Can not truncate file " + path);
        }
    }
}
