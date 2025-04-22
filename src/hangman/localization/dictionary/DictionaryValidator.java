package hangman.localization.dictionary;

public class DictionaryValidator implements Validator<String> {

    protected String regex;

    public DictionaryValidator(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean isValid(String value) {
        return value.matches(regex);
    }
}
