package hangman.core;

import java.util.List;

import hangman.io.GameReader;
import hangman.io.GameWriter;
import hangman.localization.provider.MessageProvider;

import java.util.Arrays;

public final class CommandInputHandlerUtils {

    private CommandInputHandlerUtils() {}

    public static String inputCommand(MessageProvider provider, GameWriter writer, GameReader reader, List<String> keys) {
        while (true) {
            String input = reader.read().trim();
            if (keys.contains(input)) {
                return input;
            }
            writer.outputMessage(provider.errorInvalidCommand(keys));
        }
    }

    public static String inputCommand(MessageProvider provider, GameWriter writer, GameReader reader, String... keys) {
        return inputCommand(provider, writer, reader, Arrays.asList(keys));
    }
}