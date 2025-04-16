package hangman.localization.Russian;

import hangman.core.entity.Letter;
import hangman.core.entity.Word;
import hangman.localization.MessageProvider;

import java.util.Set;

import static java.util.stream.Collectors.joining;

public class RussianMessageProvider implements MessageProvider {

    @Override
    public String promptLetterInput() {
        return "Введите букву русского алфавита (а-я)!";
    }

    @Override
    public String errorWrongLength() {
        return "Ошибка: нужно ввести ровно один символ.";
    }

    @Override
    public String errorInvalidLetter() {
        return "Ошибка: допустимы только буквы русского алфавита (а-я).";
    }

    @Override
    public String errorLetterAlreadyEntered(char letter) {
        return String.format("Ошибка: буква %c уже была введена!", letter);
    }

    @Override
    public String correctLetterMessage(char letter) {
        return String.format("Буква %c угадана правильно!", letter);
    }

    @Override
    public String incorrectLetterMessage(char letter) {
        return String.format("Буквы %c нет в слове!", letter);
    }

    @Override
    public String winMessage(String word) {
        return "Вы победили! Загаданное слово: " + word;
    }

    @Override
    public String loseMessage(String word) {
        return "Вы проиграли! Загаданное слово: " + word;
    }

    @Override
    public String promptMainMenu() {
        return "Введите 1, чтобы начать новую игру!\nВведите 2, чтобы выйти из приложения!";
    }

    @Override
    public String errorInvalidCommand() {
        return "Ошибка: допустимы только команды 1 и 2.";
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

