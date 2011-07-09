package dk.clausreimer.zmag.wordrank;

/**
 * Created by IntelliJ IDEA.
 * User: claus
 * Date: 09-07-11
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class WordRankException extends RuntimeException {
    public WordRankException() {
        super();
    }

    public WordRankException(String message) {
        super(message);
    }

    public WordRankException(String message, Throwable cause) {
        super(message, cause);
    }

    public WordRankException(Throwable cause) {
        super(cause);
    }
}
