package dk.clausreimer.zmag.wordrank;

import java.util.Comparator;
import java.util.Map;


public class WordRankComparator implements Comparator<String> {
    private Map<String, Integer> map;

    public WordRankComparator(Map<String, Integer> map) {
        this.map = map;
    }

    public int compare(String o1, String o2) {
        return map.get(o1).compareTo(map.get(o2)) * -1;
    }
}
