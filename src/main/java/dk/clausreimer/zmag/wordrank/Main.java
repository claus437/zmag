package dk.clausreimer.zmag.wordrank;

import java.io.File;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        WordRank ranker;
        WordRankPrinter printer;
        Map<String, Integer> rank;
        String text;

        ranker = new WordRank();
        text = IOUtil.read(new File("src/main/resources/mostly-harmless.txt"));

        ranker.addText(text);
        rank = ranker.getRank();

        printer = new WordRankPrinter(System.out);
        printer.print(rank, 10);
    }
}
