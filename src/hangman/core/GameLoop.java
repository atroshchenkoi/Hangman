package hangman.core;

import hangman.io.*;
import java.util.Optional;

public class GameLoop {

    private final WordPool wordPool;
    private final GameReader reader;
    private final GameWriter writer;

    public GameLoop(GameReader reader, GameWriter writer, WordPool wordPool) {
        this.reader = reader;
        this.writer = writer;
        this.wordPool = wordPool;
    }
    public void run() {
        while(true) {
            writer.outputMessage(
                    "Введите 1, чтобы начать новую игру!\n" +
                    "Введите 2, чтобы выйти из приложения!"
            );
            Optional<Character> commandOptional = Optional.empty();
            while (commandOptional.isEmpty()) {
                String input = reader.readRawInput();
                commandOptional = validateInput(input);
            }
            char command = commandOptional.get();
            if(command == '1') {
                String word = wordPool.getRandomWord();
                Game game = new Game(reader, writer, word);
                game.run();
            } else if (command == '2') {
                break;
            }
        }
    }

    private Optional<Character> validateInput(String input) {
        Optional<Character> command = Optional.empty();
        if (input.length() != 1) {
            writer.outputMessage("Ошибка: введите одно число.");
            return command;
        }
        char ch = input.charAt(0);
        if (ch != '1' && ch != '2') {
            writer.outputMessage("Ошибка: допустимы только команды 1 и 2.");
            return command;
        }
        command = Optional.of(ch);
        return command;
    }
}
