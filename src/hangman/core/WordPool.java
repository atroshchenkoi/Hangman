package hangman.core;

import hangman.io.*;
import java.util.List;
import java.util.Random;

public class WordPool {

    private final List<String> words;
    private final Random random = new Random();

    public WordPool(WordReader reader) {
        List<String> allWords = reader.readWords().stream()
                .map(String::toLowerCase)
                .toList();
        List<String> validWords = allWords.stream()
                .filter(WordPool::validateWord)
                .toList();
        if (validWords.isEmpty()) {
            throw new IllegalStateException("Нет допустимых слов после фильтрации списка слов...");
        }
        this.words = validWords;
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

    private static boolean validateWord(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        if (word.length() < 5 || word.length() > 15) {
            return false;
        }
        return word.matches("[а-яё]+");
    }
}
