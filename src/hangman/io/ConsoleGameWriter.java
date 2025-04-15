package hangman.io;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.joining;


public class ConsoleGameWriter implements GameWriter {

    private static final String[] HANGMAN_STAGES = {
            """
    
     
    
    
    
    =========
    """,
            """
    
    |
    |
    |
    |
    =========
    """,
            """
    +---+
    |
    |
    |
    |
    =========
    """,
            """
    +---+
    |   |
    |
    |
    |
    =========
    """,
            """
    +---+
    |   |
    |   O
    |
    |
    =========
    """,
            """
    +---+
    |   |
    |   O
    |   |
    |
    =========
    """,
            """
    +---+
    |   |
    |   O
    |  /|
    |
    =========
    """,
            """
    +---+
    |   |
    |   O
    |  /|\\
    |
    =========
    """,
            """
    +---+
    |   |
    |   O
    |  /|\\
    |  /
    =========
    """,
            """
    +---+
    |   |
    |   O
    |  /|\\
    |  / \\
    =========
    """
    };

    @Override
    public void outputCurrentState(int errorCount, String word, Set<Character> enteredLetters, Map<Character, Boolean> revealedLetters) {
        String newWord = word.chars()
                .mapToObj(c -> (char) c)
                .map(ch -> revealedLetters.getOrDefault(ch, false) ? String.valueOf(ch) : "_")
                .collect(joining());
        System.out.println(HANGMAN_STAGES[errorCount] +
                "\nКоличество ошибок: " + errorCount +
                "\nВведенные символы: " + enteredLetters.stream().map(Object::toString).collect(joining(", ")) +
                "\nТекущее слово: " + newWord);
    }

    @Override
    public void outputMessage(String message) {
        System.out.println(message);
    }
}
