package hangman.localization.provider;

import hangman.core.GameLoop;
import hangman.core.entity.Letter;
import hangman.core.entity.Word;
import hangman.localization.dictionary.DictionaryLanguage;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.joining;

public class RussianMessageProvider implements MessageProvider {

    @Override
    public String promptLetterInput(DictionaryLanguage dictionaryLanguage) {
        return String.format("Введите строчную букву (%s)!", dictionaryLanguage.getRegex());
    }

    @Override
    public String errorWrongLength() {
        return "Ошибка: нужно ввести ровно один символ.";
    }

    @Override
    public String errorInvalidLetter(DictionaryLanguage dictionaryLanguage) {
        return String.format("Ошибка: допустимы только строчные буквы: (%s).", dictionaryLanguage.getUserRegex());
    }

    @Override
    public String errorLetterAlreadyEntered(Letter letter) {
        return String.format("Ошибка: буква %c уже была введена!", letter.getValue());
    }

    @Override
    public String correctLetterMessage(Letter letter) {
        return String.format("Буква %c угадана правильно!", letter.getValue());
    }

    @Override
    public String incorrectLetterMessage(Letter letter) {
        return String.format("Буквы %c нет в слове!", letter.getValue());
    }

    @Override
    public String winMessage(Word word) {
        return "Вы победили! Загаданное слово: " + word.getFullValue();
    }

    @Override
    public String loseMessage(Word word) {
        return "Вы проиграли! Загаданное слово: " + word.getFullValue();
    }

    @Override
    public String promptMainMenu() {
        return String.format("Введите %s, чтобы начать новую игру!" +
                            "\nВведите %s, чтобы выйти из приложения!",
                            GameLoop.COMMAND_START_GAME,
                            GameLoop.COMMAND_END_GAME);
    }

    @Override
    public String promptDictionaryLanguageSelectMenu() {
        String startText = "Выберите язык угадываемых слов:\n";
        List<String> dictionaryLanguagesInfo = Arrays.stream(DictionaryLanguage.values())
                .map(el -> "Код: " + el.toString())
                .toList();
        return startText + String.join("\n", dictionaryLanguagesInfo);
    }

    @Override
    public String promptProviderLanguageSelectMenu() {
        String startText = "Выберите язык интерфейса:\n";
        List<String> providerLanguagesInfo = Arrays.stream(ProviderLanguage.values())
                .map(el -> "Код: " + el.toString())
                .toList();
        return startText + String.join("\n", providerLanguagesInfo);
    }

    @Override
    public String errorInvalidCommand(List<String> validKeys) {
        return String.format(
                "Ошибка: недопустимая команда. Допустимые команды: %s",
                String.join(", ", validKeys)
        );
    }

    @Override
    public String errorInvalidCommand(String... validKeys) {
        return errorInvalidCommand(Arrays.asList(validKeys));
    }

    @Override
    public String currentGameState(int errorCount, Set<Letter> enteredLetters, Word word) {
        return  "\nКоличество ошибок: " + errorCount +
                "\nВведенные символы: " + enteredLetters.stream()
                                                    .map(Letter::toString)
                                                    .collect(joining(", ")) +
                "\nТекущее слово: " + word.getMaskedValue();
    }
}

