package hangman.localization.dictionary;

import hangman.localization.provider.ProviderLanguage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DictionaryLanguage {

    RU("Русский", "ru", "[а-яё]+", "Русский алфавит: а-я"),
    EN("English", "en","[a-z]+", "English alphabet: a-z");

    private final String name;
    private final String code;
    private final String regex;
    private final String userRegex;

    DictionaryLanguage(String name, String code, String regex, String userRegex) {
        this.name = name;
        this.code = code;
        this.regex = regex;
        this.userRegex = userRegex;
    }

    public String getRegex() {
        return regex;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getUserRegex() {
        return userRegex;
    }
    @Override
    public String toString() {
        return String.format("%s. %s", code, userRegex);
    }

    public static List<String> getDictionaryLanguagesCodes() {
        return Arrays.stream(DictionaryLanguage.values())
                .map(DictionaryLanguage::getCode)
                .collect(Collectors.toList());
    }

    public static DictionaryLanguage getByCode(String code) {
        return Arrays.stream(values())
                .filter(lang -> lang.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown language code: " + code));
    }
}
