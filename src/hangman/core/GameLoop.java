package hangman.core;

import hangman.core.entity.Word;
import hangman.io.*;
import hangman.io.util.CommandInputHandlerUtils;
import hangman.localization.dictionary.DictionaryLanguageValidator;
import hangman.localization.provider.MessageProvider;

public class GameLoop {

    public final static String COMMAND_START_GAME = "1";
    public final static String COMMAND_END_GAME = "2";

    private final GameReader reader;
    private final GameWriter writer;
    private final MessageProvider messageProvider;
    private final DictionaryLanguageValidator dictionaryLanguageValidator;
    private final WordPool wordPool;

    public GameLoop(GameReader reader, GameWriter writer, MessageProvider messageProvider, DictionaryLanguageValidator dictionaryLanguageValidator, WordPool wordPool) {
        this.reader = reader;
        this.writer = writer;
        this.messageProvider = messageProvider;
        this.dictionaryLanguageValidator = dictionaryLanguageValidator;
        this.wordPool = wordPool;
    }

    public void start() {
        while(true) {
            String promptMainMenuMessage = messageProvider.promptMainMenu();
            writer.outputMessage(promptMainMenuMessage);
            String command = CommandInputHandlerUtils.inputCommand(
                    messageProvider,
                    writer,
                    reader,
                    COMMAND_START_GAME,
                    COMMAND_END_GAME
            );
            if(command.equals(COMMAND_START_GAME)) {
                Word word = wordPool.getRandomWord();
                Game game = new Game(reader, writer, messageProvider, dictionaryLanguageValidator, word);
                game.start();
            } else if (command.equals(COMMAND_END_GAME)) {
                break;
            }
        }
    }
}
