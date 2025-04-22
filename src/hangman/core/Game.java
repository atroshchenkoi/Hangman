package hangman.core;

import hangman.core.entity.Letter;
import hangman.core.entity.Word;
import hangman.io.*;
import hangman.localization.dictionary.DictionaryValidator;
import hangman.localization.provider.*;

import java.util.*;

public class Game {

    private static final int MAX_ERROR_COUNT = 9;
    private static final int START_ERROR_COUNT = 0;

    private final GameReader reader;
    private final GameWriter writer;
    private final DictionaryValidator validator;
    private final MessageProvider provider;

    private int errorCount;
    private final Word word;
    private final Set<Letter> enterLetters;

    public Game(GameReader reader, GameWriter writer, MessageProvider provider, DictionaryValidator validator, Word word) {
        this.reader = reader;
        this.writer = writer;
        this.validator = validator;
        this.provider = provider;
        this.errorCount = START_ERROR_COUNT;
        this.word = word;
        this.enterLetters = new TreeSet<>();
    };

    public void start() {
        writer.outputHangmanStage(errorCount);
        String messageGameState = provider.currentGameState(errorCount, enterLetters, word);
        writer.outputMessage(messageGameState);

        while (!isEndGame()) {
            Letter letter = inputLetter();
            putLetter(letter);
            writer.outputHangmanStage(errorCount);
            writer.outputMessage(provider.currentGameState(errorCount, enterLetters, word));
        }
        if (isWin()) {
            writer.outputMessage(provider.winMessage(word));
        } else {
            writer.outputMessage(provider.loseMessage(word));
        }
    }



    private Letter inputLetter() {
        Optional<Character> inputCharOptional = Optional.empty();
        while (inputCharOptional.isEmpty()) {
            String messagePromptLetterInput = provider.promptLetterInput();
            writer.outputMessage(messagePromptLetterInput);
            String input = reader.read();
            inputCharOptional = validateInput(input);
        }
        return new Letter(inputCharOptional.get());
    }

    private Optional<Character> validateInput(String input) {
        if (input.length() != 1) {
            String messageWrongLength = provider.errorWrongLength();
            writer.outputMessage(messageWrongLength);
            return Optional.empty();
        }
        char inputChar = input.charAt(0);
        if (!validator.isValid(String.valueOf(inputChar))) {
            String messageInvalidLetter = provider.errorInvalidLetter();
            writer.outputMessage(messageInvalidLetter);
            return Optional.empty();
        }
        return Optional.of(inputChar);
    }

    private void putLetter(Letter letter) {
        if (enterLetters.contains(letter)) {
            writer.outputMessage(provider.errorLetterAlreadyEntered(letter));
            return;
        }
        enterLetters.add(letter);
        if (word.containsLetter(letter)) {
            word.revealLetter(letter);
            writer.outputMessage(provider.correctLetterMessage(letter));
        } else {
            errorCount++;
            writer.outputMessage(provider.incorrectLetterMessage(letter));
        }
    }

    private boolean isWin() {
        return errorCount < MAX_ERROR_COUNT && word.allLettersVisible();
    }

    private boolean isEndGame() {
        return errorCount >= MAX_ERROR_COUNT || word.allLettersVisible();
    }

}
