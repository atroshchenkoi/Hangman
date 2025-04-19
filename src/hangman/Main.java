package hangman;

import hangman.io.*;
import hangman.core.*;
import hangman.io.console.ConsoleGameReader;
import hangman.io.console.ConsoleGameWriter;
import hangman.io.file.FileTextReader;
import hangman.io.util.CommandInputHandlerUtils;
import hangman.localization.dictionary.DictionaryLanguage;
import hangman.localization.dictionary.DictionaryLanguageValidator;
import hangman.localization.provider.EnglishMessageProvider;
import hangman.localization.provider.MessageProvider;
import hangman.localization.provider.MessageProviderFactory;
import hangman.localization.provider.ProviderLanguage;

public class Main {
    public static void main(String[] args) {

        GameWriter gameWriter = new ConsoleGameWriter();
        GameReader gameReader = new ConsoleGameReader();
        MessageProvider baseMessageProvider = new EnglishMessageProvider();

        MessageProvider gameMessageProvider = selectMessageProvider(baseMessageProvider, gameWriter, gameReader);
        DictionaryLanguageValidator gameDictionaryLanguageValidator = selectDictionaryLanguageValidator(gameMessageProvider, gameWriter, gameReader);

        TextReader gameTextReader = new FileTextReader(gameDictionaryLanguageValidator.getLanguage());
        WordPool gameWordPool = new WordPool(gameTextReader, gameDictionaryLanguageValidator);

        GameLoop gameLoop = new GameLoop(gameReader, gameWriter, gameMessageProvider, gameDictionaryLanguageValidator, gameWordPool);
        gameLoop.start();
    }

    private static MessageProvider selectMessageProvider(MessageProvider baseMessageProvider, GameWriter writer, GameReader reader) {
        String title = baseMessageProvider.promptProviderLanguageSelectMenu();
        writer.outputMessage(title);
        String providerCode = CommandInputHandlerUtils.inputCommand(
                baseMessageProvider,
                writer,
                reader,
                ProviderLanguage.getProviderLanguagesCodes()
        );
        ProviderLanguage providerLanguage = ProviderLanguage.getByCode(providerCode);
        return MessageProviderFactory.getByLanguage(providerLanguage);
    }

    private static DictionaryLanguageValidator selectDictionaryLanguageValidator(MessageProvider messageProvider, GameWriter writer, GameReader reader) {
        String title = messageProvider.promptDictionaryLanguageSelectMenu();
        writer.outputMessage(title);
        String dictionaryLanguageCode =  CommandInputHandlerUtils.inputCommand(
                messageProvider,
                writer,
                reader,
                DictionaryLanguage.getDictionaryLanguagesCodes()
        );
        DictionaryLanguage dictionaryLanguage = DictionaryLanguage.getByCode(dictionaryLanguageCode);
        return new DictionaryLanguageValidator(dictionaryLanguage);
    }
}