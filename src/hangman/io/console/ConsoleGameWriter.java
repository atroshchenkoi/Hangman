package hangman.io.console;

import hangman.io.GameWriter;

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
    public void outputHangmanStage(int errorCount) {
        System.out.println(HANGMAN_STAGES[errorCount]);
    }

    @Override
    public void outputMessage(String message) {
        System.out.println(message);
    }
}
