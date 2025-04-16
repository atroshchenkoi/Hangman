package hangman.core;

import hangman.core.entity.Letter;
import hangman.core.entity.Word;
import hangman.io.*;
import hangman.localization.LanguageContext;
import hangman.localization.LanguageValidator;
import hangman.localization.MessageProvider;

import java.util.*;

public class Game {

    private static final int MAX_ERROR_COUNT = 9;

    private final GameReader reader;
    private final GameWriter writer;
    private final LanguageValidator languageValidator;
    private final MessageProvider messageProvider;

    private int errorCount;
    private final Word word;
    private final Set<Letter> enterLetters;

    public Game(GameReader reader, GameWriter writer, LanguageContext languageContext, Word word) {
        this.reader = reader;
        this.writer = writer;
        this.languageValidator = languageContext.getValidator();
        this.messageProvider = languageContext.getMessageProvider();
        this.errorCount = 0;
        this.word = word;
        this.enterLetters = new TreeSet<>();
    };

    public void run() {
        writer.outputHangmanStage(errorCount);
        writer.outputMessage(messageProvider.currentGameState(errorCount, enterLetters, word));

        while (errorCount < MAX_ERROR_COUNT && word.getLetters().stream().anyMatch(l -> !l.isVisible())) {
            Optional<Character> inputCharOptional = Optional.empty();

            while (inputCharOptional.isEmpty()) {
                writer.outputMessage(messageProvider.promptLetterInput());
                String input = reader.readRawInput();
                inputCharOptional = validateInput(input);
            }

            Letter letter = new Letter(inputCharOptional.get());
            putLetter(letter);

            writer.outputHangmanStage(errorCount);
            writer.outputMessage(messageProvider.currentGameState(errorCount, enterLetters, word));
        }

        if (errorCount == MAX_ERROR_COUNT) {
            writer.outputMessage(messageProvider.loseMessage(word.getFullValue()));
        } else {
            writer.outputMessage(messageProvider.winMessage(word.getFullValue()));
        }
    }


    private Optional<Character> validateInput(String input) {
        if (input.length() != 1) {
            writer.outputMessage(messageProvider.errorWrongLength());
            return Optional.empty();
        }

        char inputChar = input.charAt(0);

        if (!languageValidator.isValidCharLetter(inputChar)) {
            writer.outputMessage(messageProvider.errorInvalidLetter());
            return Optional.empty();
        }

        if (enterLetters.contains(new Letter(inputChar))) {
            writer.outputMessage(messageProvider.errorLetterAlreadyEntered(inputChar));
            return Optional.empty();
        }

        return Optional.of(inputChar);
    }

    private void putLetter(Letter letter) {
        enterLetters.add(letter);
        if (word.containsLetter(letter)) {
            word.revealLetter(letter);
            writer.outputMessage(messageProvider.correctLetterMessage(letter.getValue()));
        } else {
            errorCount++;
            writer.outputMessage(messageProvider.incorrectLetterMessage(letter.getValue()));
        }
    }

}
