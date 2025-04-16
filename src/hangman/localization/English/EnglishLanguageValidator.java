package hangman.localization.English;

import hangman.localization.LanguageValidator;

public class EnglishLanguageValidator implements LanguageValidator {

    @Override
    public boolean isValidCharLetter(char letter) {
        return (letter >= 'a' && letter <= 'z');
    }

    @Override
    public boolean isValidStringWord(String word) {
        return word.matches("[a-z]+");
    }
}
