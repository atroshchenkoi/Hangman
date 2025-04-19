package hangman.localization.provider;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProviderLanguage {

    RU("Русский", "ru"),
    EN("English", "en");

    private final String name;
    private final String code;

    ProviderLanguage(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", code, name);
    }

    public static List<String> getProviderLanguagesCodes() {
        return Arrays.stream(ProviderLanguage.values())
                .map(ProviderLanguage::getCode)
                .collect(Collectors.toList());
    }


    public static ProviderLanguage getByCode(String code) {
        return Arrays.stream(values())
                .filter(lang -> lang.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown language code: " + code));
    }
}
