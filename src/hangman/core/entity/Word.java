package hangman.core.entity;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class Word {

    private final List<Letter> letters;

    public Word(String word) {
        this.letters = word.chars()
                .mapToObj(el -> new Letter((char) el))
                .toList();

    }

    public List<Letter> getLetters() {
        return letters;
    }

    public boolean containsLetter(Letter letter) {
        return letters.stream()
                .anyMatch(el -> el.getValue() == letter.getValue());
    }

    public void revealLetter(Letter letter) {
        letters.stream()
                .filter(el -> el.getValue() == letter.getValue())
                .forEach(el -> el.setVisible(true));
    }

    public boolean allLettersVisible() {
        return letters.stream()
                .allMatch(Letter::isVisible);
    }

    public String getFullValue() {
        return letters.stream()
                .map(el -> String.valueOf(el.getValue()))
                .collect(joining());
    }

    public String getMaskedValue() {
        return letters.stream()
                .map(el -> el.isVisible() ? String.valueOf(el.getValue()) : "_")
                .collect(joining());
    }
}
