package hangman.io.dialog;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class StringSelectDialog implements Dialog<String> {

    private final Supplier<String> reader;
    private final Consumer<String> writer;

    private final String title;
    private final String failMessage;
    private final List<String> keys;

    public StringSelectDialog(Supplier<String> reader, Consumer<String> writer, String title, String failMessage, List<String> keys) {
        this.reader = reader;
        this.writer = writer;
        this.title = title;
        this.failMessage = failMessage;
        this.keys = keys;
    }

    public StringSelectDialog(Supplier<String> reader, Consumer<String> writer, String title, String failMessage, String... keys) {
        this(reader, writer, title, failMessage, Arrays.asList(keys));
    }

    @Override
    public String input() {
        writer.accept(title);
        while (true) {
            String input = reader.get();
            for (String key : keys) {
                if (input.equalsIgnoreCase(key)) {
                    return input;
                }
            }
            writer.accept(failMessage);
        }
    }

    public String input(String regex) {
        writer.accept(title);
        while (true) {
            String input = reader.get();
            if(input.matches(regex)) {
                    return input;
                }
            writer.accept(failMessage);
        }
    }
}
