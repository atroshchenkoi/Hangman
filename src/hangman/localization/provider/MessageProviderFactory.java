package hangman.localization.provider;

import hangman.localization.Language;

public final class MessageProviderFactory {

    private MessageProviderFactory() {}

    public static MessageProvider get(Language language) {
        return switch (language) {
            case RU -> new RussianMessageProvider();
            case EN -> new EnglishMessageProvider();
            default -> throw new IllegalArgumentException("Unknown language " + language);
        };
    }
}
