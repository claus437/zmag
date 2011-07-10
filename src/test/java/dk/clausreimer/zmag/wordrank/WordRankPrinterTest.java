package dk.clausreimer.zmag.wordrank;

import org.junit.Test;

import junit.framework.Assert;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;


public class WordRankPrinterTest {
    private ByteArrayOutputStream result = new ByteArrayOutputStream();
    private WordRankPrinter printer = new WordRankPrinter(new PrintStream(result));

    @Test
    public void testPrintFewerThanRequested() {
        Map<String, Integer> values;
        String expected;

        values = new TreeMap<String, Integer>();
        values.put("line-1", 10);
        values.put("line-2", 11);

        printer.print(values, 3);

        expected = " # | Hits | Word                        \n" +
                   "---+------+-----------------------------\n" +
                   " 1 |   10 | line-1                      \n" +
                   " 2 |   11 | line-2                      \n" +
                   "----------------------------------------\n";

        Assert.assertEquals(expected, new String(result.toByteArray()).replaceAll("\\r", ""));
    }

    @Test
    public void testPrintMoreThanRequest() {
        Map<String, Integer> values;
        String expected;

        values = new TreeMap<String, Integer>();
        values.put("line-a", 10);
        values.put("line-b", 9);
        values.put("line-c", 8);

        printer.print(values, 2);

        expected = " # | Hits | Word                        \n" +
                   "---+------+-----------------------------\n" +
                   " 1 |   10 | line-a                      \n" +
                   " 2 |    9 | line-b                      \n" +
                   "----------------------------------------\n";

        Assert.assertEquals(expected, new String(result.toByteArray()).replaceAll("\\r", ""));
    }
}
