package hangman.io;

import java.util.Scanner;

public class ConsoleGameReader implements GameReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readRawInput() {
        return scanner.nextLine().trim();
    }
}


