package hangman.localization;

import hangman.core.entity.Letter;
import hangman.core.entity.Word;

import java.util.Set;

public interface MessageProvider {
    String promptLetterInput();
    String errorWrongLength();
    String errorInvalidLetter();
    String errorLetterAlreadyEntered(char letter);
    String correctLetterMessage(char letter);
    String incorrectLetterMessage(char letter);
    String winMessage(String word);
    String loseMessage(String word);
    String promptMainMenu();
    String errorInvalidCommand();
    String currentGameState(int errorCount, Set<Letter> enteredLetters, Word word);
}
