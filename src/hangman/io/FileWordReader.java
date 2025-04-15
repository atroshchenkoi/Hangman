package hangman.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileWordReader implements WordReader {

    private final String filePath;

    public FileWordReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> readWords() {
        try {
            String content = Files.readString(Path.of(filePath));
            return Stream.of(content.split("\\s+"))
                    .filter(word -> !word.isBlank())
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл слов по пути: " + filePath, e);
        }
    }
}
