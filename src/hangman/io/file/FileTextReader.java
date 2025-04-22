package hangman.io.file;

import hangman.io.TextReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileTextReader implements TextReader {

    private final String filePath;

    public FileTextReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> read() {
        try {
            String content = Files.readString(Path.of(filePath));
            return Stream.of(content.split("\\s+"))
                    .filter(word -> !word.isBlank())
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read the word file on the way: " + filePath, e);
        }
    }
}
