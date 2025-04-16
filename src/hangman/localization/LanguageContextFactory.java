package hangman.localization;

import hangman.io.GameReader;
import hangman.io.GameWriter;
import hangman.localization.English.EnglishLanguageValidator;
import hangman.localization.English.EnglishMessageProvider;
import hangman.localization.Russian.RussianLanguageValidator;
import hangman.localization.Russian.RussianMessageProvider;

import java.util.Set;

public class LanguageContextFactory {

    public static final Set<String> SUPPORTED_CODES = Set.of("ru", "en");

    public static boolean isSupported(String code) {
        return SUPPORTED_CODES.contains(code);
    }

    public static LanguageContext create(String code) {
        return switch (code) {
            case "ru" -> new LanguageContext(new RussianLanguageValidator(), new RussianMessageProvider());
            case "en" -> new LanguageContext(new EnglishLanguageValidator(), new EnglishMessageProvider());
            default -> throw new IllegalArgumentException("Неверный код языка: " + code);
        };
    }

    public static LanguageContext chooseContext(GameReader reader, GameWriter writer) {
        while (true) {
            writer.outputMessage(
                    "Введите код языка. Доступные языки для слов (код): " + SUPPORTED_CODES +
                    "\nChoose language code: Available languages (code): " + SUPPORTED_CODES
            );
            String input = reader.readRawInput().toLowerCase();
            if (isSupported(input)) {
                return create(input);
            } else {
                writer.outputMessage(
                        "Неподдерживаемый язык. Доступные языки (код): " + SUPPORTED_CODES +
                        "\nInvalid code. Available languages (code): " + SUPPORTED_CODES
                );
            }
        }
    }
}
