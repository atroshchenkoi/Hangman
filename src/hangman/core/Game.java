package hangman.core;

import hangman.core.entity.Letter;
import hangman.core.entity.Word;
import hangman.io.*;
import hangman.localization.provider.*;
import hangman.localization.dictionary.*;

import java.util.*;

public class Game {

    private static final int MAX_ERROR_COUNT = 9;
    private static final int START_ERROR_COUNT = 0;

    private final GameReader reader;
    private final GameWriter writer;
    private final DictionaryLanguageValidator dictionaryLanguageValidator;
    private final MessageProvider messageProvider;

    private int errorCount;
    private final Word word;
    private final Set<Letter> enterLetters;

    public Game(GameReader reader, GameWriter writer, MessageProvider messageProvider, DictionaryLanguageValidator dictionaryLanguageValidator, Word word) {
        this.reader = reader;
        this.writer = writer;
        this.dictionaryLanguageValidator = dictionaryLanguageValidator;
        this.messageProvider = messageProvider;
        this.errorCount = START_ERROR_COUNT;
        this.word = word;
        this.enterLetters = new TreeSet<>();
    };

    public void start() {
        writer.outputHangmanStage(errorCount);
        String messageGameState = messageProvider.currentGameState(errorCount, enterLetters, word);
        writer.outputMessage(messageGameState);

        while (!isEndGame()) {
            Letter letter = inputLetter();
            putLetter(letter);
            writer.outputHangmanStage(errorCount);
            writer.outputMessage(messageProvider.currentGameState(errorCount, enterLetters, word));
        }
        if (isWin()) {
            writer.outputMessage(messageProvider.winMessage(word));
        } else {
            writer.outputMessage(messageProvider.loseMessage(word));
        }
    }



    private Letter inputLetter() {
        Optional<Character> inputCharOptional = Optional.empty();
        while (inputCharOptional.isEmpty()) {
            String messagePromptLetterInput = messageProvider.promptLetterInput(dictionaryLanguageValidator.getLanguage());
            writer.outputMessage(messagePromptLetterInput);
            String input = reader.read();
            inputCharOptional = validateInput(input);
        }
        return new Letter(inputCharOptional.get());
    }

    private Optional<Character> validateInput(String input) {
        if (input.length() != 1) {
            String messageWrongLength = messageProvider.errorWrongLength();
            writer.outputMessage(messageWrongLength);
            return Optional.empty();
        }
        char inputChar = input.charAt(0);
        if (!dictionaryLanguageValidator.isValidString(String.valueOf(inputChar))) {
            String messageInvalidLetter = messageProvider.errorInvalidLetter(dictionaryLanguageValidator.getLanguage());
            writer.outputMessage(messageInvalidLetter);
            return Optional.empty();
        }
        return Optional.of(inputChar);
    }

    private void putLetter(Letter letter) {
        if (enterLetters.contains(letter)) {
            writer.outputMessage(messageProvider.errorLetterAlreadyEntered(letter));
            return;
        }
        enterLetters.add(letter);
        if (word.containsLetter(letter)) {
            word.revealLetter(letter);
            writer.outputMessage(messageProvider.correctLetterMessage(letter));
        } else {
            errorCount++;
            writer.outputMessage(messageProvider.incorrectLetterMessage(letter));
        }
    }

    private boolean isWin() {
        return errorCount < MAX_ERROR_COUNT && word.allLettersVisible();
    }

    private boolean isEndGame() {
        return errorCount >= MAX_ERROR_COUNT || word.allLettersVisible();
    }

}
