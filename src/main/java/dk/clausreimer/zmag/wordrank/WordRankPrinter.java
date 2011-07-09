package dk.clausreimer.zmag.wordrank;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: claus
 * Date: 09-07-11
 * Time: 19:12
 * To change this template use File | Settings | File Templates.
 */
public class WordRankPrinter {
    PrintStream out;

    public WordRankPrinter(PrintStream out) {
        this.out = out;
    }

    public void print(Map<String, Integer> values, int max) {
        Iterator<String> keys;
        String key;

        keys = values.keySet().iterator();

        printHeader();

        for (int i = 0; keys.hasNext() && i < max ; i ++) {
            key = keys.next();

            out.printf("%2s | %4s | %-28s\n", i + 1, values.get(key), key);
        }

        printFooter();
    }

    private void printHeader() {
        out.println(" # | Hits | Word                        ");
        out.println("---+------+-----------------------------");
    }

    private void printFooter() {
        out.println("----------------------------------------");
    }


}
