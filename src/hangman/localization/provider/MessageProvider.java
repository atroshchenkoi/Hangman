package hangman.localization.provider;

import hangman.core.entity.Letter;
import hangman.core.entity.Word;
import hangman.localization.dictionary.DictionaryLanguage;

import java.util.List;
import java.util.Set;

public interface MessageProvider {

    String promptLetterInput(DictionaryLanguage dictionaryLanguage);
    String errorWrongLength();
    String errorInvalidLetter(DictionaryLanguage dictionaryLanguage);
    String errorLetterAlreadyEntered(Letter letter);
    String correctLetterMessage(Letter letter);
    String incorrectLetterMessage(Letter letter);
    String winMessage(Word word);
    String loseMessage(Word word);
    String promptMainMenu();
    String promptDictionaryLanguageSelectMenu();
    String promptProviderLanguageSelectMenu();
    String errorInvalidCommand(List<String> validKeys);
    String errorInvalidCommand(String... validKeys);
    String currentGameState(int errorCount, Set<Letter> enteredLetters, Word word);
}
