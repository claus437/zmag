package dk.clausreimer.zmag.wordrank;


public class WordRankException extends RuntimeException {
    public WordRankException(String message) {
        super(message);
    }

    public WordRankException(String message, Throwable cause) {
        super(message, cause);
    }
}
