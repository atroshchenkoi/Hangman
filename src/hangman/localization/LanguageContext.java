package hangman.localization;

public class LanguageContext {
    private final LanguageValidator validator;
    private final MessageProvider messages;

    public LanguageContext(LanguageValidator validator, MessageProvider messages) {
        this.validator = validator;
        this.messages = messages;
    }

    public LanguageValidator getValidator() {
        return validator;
    }

    public MessageProvider getMessageProvider() {
        return messages;
    }
}
