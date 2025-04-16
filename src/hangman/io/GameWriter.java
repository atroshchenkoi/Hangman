package hangman.io;

public interface GameWriter {
    public void outputHangmanStage(int errorCount);

    public void outputMessage(String message);
}
