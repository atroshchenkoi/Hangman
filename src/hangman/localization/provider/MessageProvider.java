package hangman.localization.provider;

import hangman.core.entity.Letter;
import hangman.core.entity.Word;

import java.util.List;
import java.util.Set;

public interface MessageProvider {

    String promptLetterInput();
    String errorWrongLength();
    String errorInvalidLetter();
    String errorLetterAlreadyEntered(Letter letter);
    String correctLetterMessage(Letter letter);
    String incorrectLetterMessage(Letter letter);
    String winMessage(Word word);
    String loseMessage(Word word);
    String promptMainMenu();
    String errorInvalidCommand();
    String currentGameState(int errorCount, Set<Letter> enteredLetters, Word word);
}
