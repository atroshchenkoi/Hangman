package hangman.localization.dictionary;

public class DictionaryLanguageValidator {

    protected DictionaryLanguage language;

    public DictionaryLanguageValidator(DictionaryLanguage language) {
        this.language = language;
    }

    public DictionaryLanguage getLanguage() {
        return language;
    }

    public boolean isValidString(String value) {
        return value.matches(language.getRegex());
    }
}
