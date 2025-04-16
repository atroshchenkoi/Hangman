package hangman.localization.Russian;

import hangman.localization.LanguageValidator;

public class RussianLanguageValidator implements LanguageValidator {

    @Override
    public boolean isValidCharLetter(char letter) {
        return ((letter >= 'а' && letter <= 'я') || letter == 'ё');
    }

    @Override
    public boolean isValidStringWord(String word) {
        return word.matches("[а-яё]+");
    }
}
