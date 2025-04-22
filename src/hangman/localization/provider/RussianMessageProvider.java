package hangman.localization.provider;

import hangman.core.GameLoop;
import hangman.core.entity.Letter;
import hangman.core.entity.Word;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.joining;

public class RussianMessageProvider implements MessageProvider {

    @Override
    public String promptLetterInput() {
        return "Введите строчную букву (а-я)!";
    }

    @Override
    public String errorWrongLength() {
        return "Ошибка: нужно ввести ровно один символ.";
    }

    @Override
    public String errorInvalidLetter() {
        return "Ошибка: допустимы только строчные буквы (а-я):";
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
    public String errorInvalidCommand() {
        return "Недопустимая команда!";
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

