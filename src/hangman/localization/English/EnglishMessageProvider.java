package hangman.localization.English;

import hangman.core.entity.Letter;
import hangman.core.entity.Word;
import hangman.localization.MessageProvider;

import java.util.Set;

import static java.util.stream.Collectors.joining;

public class EnglishMessageProvider implements MessageProvider {

    @Override
    public String promptLetterInput() {
        return "Enter a letter (a-z):";
    }

    @Override
    public String errorWrongLength() {
        return "Error: Please enter exactly one character.";
    }

    @Override
    public String errorInvalidLetter() {
        return "Error: Only English letters (a-z) are allowed.";
    }

    @Override
    public String errorLetterAlreadyEntered(char letter) {
        return String.format("Error: The letter '%c' has already been entered!", letter);
    }

    @Override
    public String correctLetterMessage(char letter) {
        return String.format("Correct! The letter '%c' is in the word.", letter);
    }

    @Override
    public String incorrectLetterMessage(char letter) {
        return String.format("Incorrect! The letter '%c' is not in the word.", letter);
    }

    @Override
    public String winMessage(String word) {
        return "Congratulations! You guessed the word: " + word;
    }

    @Override
    public String loseMessage(String word) {
        return "Game over! The correct word was: " + word;
    }

    @Override
    public String promptMainMenu() {
        return "Enter 1 to start a new game.\n" +
                "Enter 2 to exit the application.";
    }

    @Override
    public String errorInvalidCommand() {
        return "Error: Valid options are 1 or 2.";
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


