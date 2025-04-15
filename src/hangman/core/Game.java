package hangman.core;

import hangman.io.*;
import java.util.*;

public class Game {

    private static final int MAX_ERROR_COUNT = 9;

    private final GameReader reader;
    private final GameWriter writer;

    private int errorCount;
    private final String word;
    private final Set<Character> enterLetters;
    private final Map<Character, Boolean> revealedLetters;

    public Game(GameReader reader, GameWriter writer, String word) {
        this.reader = reader;
        this.writer = writer;
        this.errorCount = 0;
        this.word = word;
        this.enterLetters = new TreeSet<>();
        this.revealedLetters = new HashMap<>();
        word.chars().mapToObj(el -> (char) el).forEach(el -> revealedLetters.put(el, false));
    };

    public void run() {
        writer.outputCurrentState(errorCount, word, enterLetters, revealedLetters);
        while (errorCount < MAX_ERROR_COUNT && !(revealedLetters.values().stream().allMatch(el -> el))) {
            Optional<Character> optionalLetter = Optional.empty();
            while (optionalLetter.isEmpty()) {
                writer.outputMessage("Введите букву (а-я)!");
                String input = reader.readRawInput();
                optionalLetter = validateInput(input);
            }
            Character letter = optionalLetter.get();
            putLetter(letter);
            writer.outputCurrentState(errorCount, word, enterLetters, revealedLetters);
        }
        if (errorCount == MAX_ERROR_COUNT) {
            writer.outputMessage("Вы проиграли! Загаданное слово: " + word);
        } else {
            writer.outputMessage("Вы победили! Загаданное слово: " + word);
        }
    }
    private Optional<Character> validateInput(String input) {
        if (input.length() != 1) {
            writer.outputMessage("Ошибка: нужно ввести ровно один символ.");
            return Optional.empty();
        }
        char ch = input.charAt(0);
        if ((ch < 'а' || ch > 'я') && ch != 'ё') {
            writer.outputMessage("Ошибка: допустимы только буквы русского алфавита (а-я).");
            return Optional.empty();
        }
        if (enterLetters.contains(ch)) {
            writer.outputMessage(String.format("Ошибка: буква %c уже была введена!", ch));
            return Optional.empty();
        }
        return Optional.of(ch);
    }

    private void putLetter(Character letter) {
        enterLetters.add(letter);
        if (revealedLetters.containsKey(letter)) {
            revealedLetters.put(letter, true);
            writer.outputMessage(String.format("Буква %c угадана правильно!", letter));
        } else {
            errorCount++;
            writer.outputMessage(String.format("Буквы %c нет в слове!", letter));
        }
    }
}
