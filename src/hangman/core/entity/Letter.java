package hangman.core.entity;

public class Letter implements Comparable<Letter> {

    private final char value;
    private boolean isVisible;

    public Letter(char value) {
        this.value = value;
        this.isVisible = false;
    }

    public char getValue() {
        return value;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Override
    public int compareTo(Letter o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
