package hangman;

import hangman.io.*;
import hangman.core.*;
import hangman.io.console.ConsoleGameReader;
import hangman.io.console.ConsoleGameWriter;

public class Main {
    public static void main(String[] args) {

        GameWriter gameWriter = new ConsoleGameWriter();
        GameReader gameReader = new ConsoleGameReader();
        WordReader wordReader = new FileWordReader("src/resources/words.txt");
        WordPool wordPool = new WordPool(wordReader);
        GameLoop gameLoop = new GameLoop(gameReader, gameWriter, wordPool);
        gameLoop.run();
    }
}