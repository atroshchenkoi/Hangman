package hangman.io.console;

public class ColorConsoleGameWriter extends ConsoleGameWriter {

    private final Color colorHangmanStage;
    private final Color colorMessage;

    public ColorConsoleGameWriter(Color colorHangmanStage, Color colorMessage) {
        this.colorHangmanStage = colorHangmanStage;
        this.colorMessage = colorMessage;
    }


    @Override
    public void outputHangmanStage(int errorCount) {
        System.out.print(colorHangmanStage.getCode());
        super.outputHangmanStage(errorCount);
        System.out.print(Color.RESET.getCode());
    }

    @Override
    public void outputMessage(String message) {
        System.out.print(colorMessage.getCode());
        super.outputMessage(message);
        System.out.print(Color.RESET.getCode());
    }

    public enum Color {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        WHITE("\u001B[37m");

        private final String code;

        Color(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
