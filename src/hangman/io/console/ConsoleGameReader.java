package hangman.io.console;

import hangman.io.GameReader;

import java.util.Scanner;

public class ConsoleGameReader implements GameReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readRawInput() {
        return scanner.nextLine().trim();
    }
}


