package hangman.localization.dictionary;

public interface Validator<T> {
    public boolean isValid(T value);
}