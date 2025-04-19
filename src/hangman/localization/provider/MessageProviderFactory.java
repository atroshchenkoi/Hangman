package hangman.localization.provider;

public final class MessageProviderFactory {

    private MessageProviderFactory() {}

    public static MessageProvider getByLanguage(ProviderLanguage language) {
        return switch (language) {
            case RU -> new RussianMessageProvider();
            case EN -> new EnglishMessageProvider();
        };
    }
}
