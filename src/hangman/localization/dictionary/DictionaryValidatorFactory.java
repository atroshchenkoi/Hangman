package hangman.localization.dictionary;

import hangman.localization.Language;

public final class DictionaryValidatorFactory {

    private DictionaryValidatorFactory() {}

    public static DictionaryValidator get(Language language) {
        return switch (language) {
            case RU -> new DictionaryValidator("[a-яё]+");
            case EN -> new DictionaryValidator("[a-z]+");
            default -> throw new IllegalArgumentException("Unknown language: " + language.name());
        };
    }
}