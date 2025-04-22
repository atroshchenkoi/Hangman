package hangman;

import hangman.core.GameLoop;
import hangman.core.WordPool;
import hangman.io.*;
import hangman.io.console.ColorConsoleGameWriter;
import hangman.io.console.ConsoleGameReader;
import hangman.io.dialog.Dialog;
import hangman.io.dialog.StringSelectDialog;
import hangman.io.file.FileTextReader;
import hangman.localization.Language;
import hangman.localization.dictionary.DictionaryValidator;
import hangman.localization.dictionary.DictionaryValidatorFactory;
import hangman.localization.provider.MessageProvider;
import hangman.localization.provider.MessageProviderFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        GameReader reader = new ConsoleGameReader();
        GameWriter writer = new ColorConsoleGameWriter(ColorConsoleGameWriter.Color.RED, ColorConsoleGameWriter.Color.GREEN);

        Language language = inputLanguage(reader, writer);
        MessageProvider messageProvider = MessageProviderFactory.get(language);
        DictionaryValidator validator = DictionaryValidatorFactory.get(language);

        TextReader textReader = new FileTextReader("src/resources/words/ru.txt");
        WordPool gameWordPool = new WordPool(textReader, validator);

        GameLoop gameLoop = new GameLoop(reader, writer, messageProvider, validator, gameWordPool);
        gameLoop.start();

    }

    private static Language inputLanguage(GameReader reader, GameWriter writer) {
        List<String> keys =  Arrays.stream(Language.values()).map(Enum::name).toList();
        String languagesCodes = String.join(", ", keys);
        String title = "Enter interface language: " + languagesCodes;
        String failMessage = "Illegal input language.";
        Dialog<String> stringDialog = new StringSelectDialog(reader::read, writer::outputMessage, title, failMessage, keys);
        String languageName = stringDialog.input().toUpperCase();
        return Language.valueOf(languageName);
    }
}