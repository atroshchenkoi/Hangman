package hangman.core;

import hangman.core.entity.Word;
import hangman.io.*;
import hangman.localization.LanguageContext;
import hangman.localization.LanguageContextFactory;
import hangman.localization.MessageProvider;

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
        LanguageContext languageContext = LanguageContextFactory.chooseContext(reader, writer);
        wordPool.loadWordsByLanguage(languageContext.getValidator());
        while(true) {
            writer.outputMessage(languageContext.getMessageProvider().promptMainMenu());
            Optional<Character> commandOptional = Optional.empty();
            while (commandOptional.isEmpty()) {
                String input = reader.readRawInput();
                commandOptional = validateInputCommand(input, languageContext.getMessageProvider());
            }
            char command = commandOptional.get();
            if(command == '1') {
                Word word = wordPool.getRandomWord();
                Game game = new Game(reader, writer, languageContext, word);
                game.run();
            } else if (command == '2') {
                break;
            }
        }
    }

    private Optional<Character> validateInputCommand(String input, MessageProvider messageProvider) {
        Optional<Character> command = Optional.empty();
        if (input.length() != 1) {
            writer.outputMessage(messageProvider.errorWrongLength());
            return command;
        }
        char ch = input.charAt(0);
        if (ch != '1' && ch != '2') {
            writer.outputMessage(messageProvider.errorInvalidCommand());
            return command;
        }
        command = Optional.of(ch);
        return command;
    }


}
