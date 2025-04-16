package hangman.core;

import hangman.core.entity.Word;
import hangman.io.*;
import hangman.localization.LanguageValidator;

import java.util.List;
import java.util.Random;

public class WordPool {

    private List<Word> words;
    private boolean wordsLoaded;
    private final WordReader reader;
    private final Random random = new Random();

    public WordPool(WordReader reader) {
        this.reader = reader;
        wordsLoaded = false;
    }

    public Word getRandomWord() {
        if (!wordsLoaded) {
            throw new IllegalStateException("Слова не загружены!");
        }
        return words.get(random.nextInt(words.size()));
    }

    public void loadWordsByLanguage(LanguageValidator languageValidator) {
        this.words = reader.readWords().stream()
                .map(String::toLowerCase)
                .filter(el -> WordPool.validateStringWord(el, languageValidator))
                .map(Word::new)
                .toList();
        if (words.isEmpty()) {
            throw new IllegalStateException("Не найдены подходящие слова после загрузки!");
        } else {
            wordsLoaded = true;
        }
    }

    private static boolean validateStringWord(String stringWord, LanguageValidator languageValidator) {
        if (stringWord == null || stringWord.isEmpty()) {
            return false;
        }
        if (stringWord.length() < 5 || stringWord.length() > 15) {
            return false;
        }
        return languageValidator.isValidStringWord(stringWord);
    }
}
