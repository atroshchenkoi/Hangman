package hangman.core;

import hangman.core.entity.Word;
import hangman.io.*;
import hangman.io.dialog.Dialog;
import hangman.io.dialog.StringSelectDialog;
import hangman.localization.dictionary.DictionaryValidator;
import hangman.localization.provider.MessageProvider;

public class GameLoop {

    public final static String COMMAND_START_GAME = "1";
    public final static String COMMAND_END_GAME = "2";

    private final GameReader reader;
    private final GameWriter writer;
    private final MessageProvider provider;
    private final DictionaryValidator validator;
    private final WordPool wordPool;

    public GameLoop(GameReader reader, GameWriter writer, MessageProvider provider, DictionaryValidator validator, WordPool wordPool) {
        this.reader = reader;
        this.writer = writer;
        this.provider = provider;
        this.validator = validator;
        this.wordPool = wordPool;
    }

    public void start() {
        while(true) {
            String menuMessage = provider.promptMainMenu();
            String failMessage = provider.errorInvalidCommand();

            Dialog<String> dialog = new StringSelectDialog(reader::read, writer::outputMessage, menuMessage, failMessage, COMMAND_START_GAME, COMMAND_END_GAME);
            String command = dialog.input();

            if(command.equals(COMMAND_START_GAME)) {
                Word word = wordPool.getRandomWord();
                Game game = new Game(reader, writer, provider, validator, word);
                game.start();
            } else if (command.equals(COMMAND_END_GAME)) {
                break;
            }
        }
    }
}
