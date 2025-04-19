package hangman.core;

import hangman.core.entity.Word;
import hangman.io.*;
import hangman.localization.dictionary.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class WordPool {

    private static final int MAX_WORD_LENGTH = 35;
    private static final int MIN_WORD_LENGTH = 5;

    private final List<Word> words;
    private final Random random = new Random();


    public WordPool(TextReader reader, DictionaryLanguageValidator validator) {
        this.words = loadAndValidateWordsFromReader(reader, validator);
    }

    public Word getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

    public List<Word> loadAndValidateWordsFromReader(TextReader reader, DictionaryLanguageValidator validator) {
        List<Word> words = reader.read().stream()
                .filter(Objects::nonNull)
                .map(String::toLowerCase)
                .map(Word::new)
                .toList();
        if(words.isEmpty()) {
            throw new IllegalStateException("No words were found when reading.");
        }
        if (!validateWords(words, validator)) {
            throw new IllegalStateException("Invalid words were found when reading.");
        }
        return words;
    }

    private static boolean validateWords(List<Word> words, DictionaryLanguageValidator validator) {
        return words.stream().allMatch(el -> validateWord(el, validator));
    }

    private static boolean validateWord(Word word, DictionaryLanguageValidator validator) {
        int wordLength = word.getFullValue().length();
        if (wordLength < MIN_WORD_LENGTH || wordLength > MAX_WORD_LENGTH) {
            return false;
        }
        return validator.isValidString(word.getFullValue());
    }
}
