package hangman.localization.provider;

import hangman.core.GameLoop;
import hangman.core.entity.Letter;
import hangman.core.entity.Word;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.joining;

public class EnglishMessageProvider implements MessageProvider {

    @Override
    public String promptLetterInput( ) {
        return "Enter a letter (a-z):";
    }

    @Override
    public String errorWrongLength() {
        return "Error: Please enter exactly one character.";
    }

    @Override
    public String errorInvalidLetter( ) {
        return "Error: (a-z) letters are allowed.";
    }

    @Override
    public String errorLetterAlreadyEntered(Letter letter) {
        return String.format("Error: The letter '%c' has already been entered!", letter.getValue());
    }

    @Override
    public String correctLetterMessage(Letter letter) {
        return String.format("Correct! The letter '%c' is in the word.", letter.getValue());
    }

    @Override
    public String incorrectLetterMessage(Letter letter) {
        return String.format("Incorrect! The letter '%c' is not in the word.", letter.getValue());
    }

    @Override
    public String winMessage(Word word) {
        return "Congratulations! You guessed the word: " + word.getFullValue();
    }

    @Override
    public String loseMessage(Word word) {
        return "Game over! The correct word was: " + word.getFullValue();
    }

    @Override
    public String promptMainMenu() {
        return String.format("Enter %s to start a new game.\n" +
                "Enter %s to exit the application.",
                GameLoop.COMMAND_START_GAME,
                GameLoop.COMMAND_END_GAME);
    }

    @Override
    public String errorInvalidCommand() {
        return "Illegal input command.";
    }

    @Override
    public String currentGameState(int errorCount, Set<Letter> enteredLetters, Word word) {
        return  "\nCount of error: " + errorCount +
                "\nEntered characters: " + enteredLetters.stream()
                                                    .map(Letter::toString)
                                                    .collect(joining(", ")) +
                "\nCurrently word: " + word.getMaskedValue();
    }


}


