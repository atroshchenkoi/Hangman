package hangman.io;

import java.util.Map;
import java.util.Set;

public interface GameWriter {
    public void outputCurrentState(int errorCount, String word, Set<Character> enteredLetters, Map<Character, Boolean> revealedLetters);

    public void outputMessage(String message);
}
