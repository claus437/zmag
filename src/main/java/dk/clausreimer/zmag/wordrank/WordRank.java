package dk.clausreimer.zmag.wordrank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordRank {
    private static final Pattern ENGLISH_WORD_PATTERN = Pattern.compile("[a-zA-Z]+");
    private Map<String, Integer> map;

    public WordRank() {
        map = new HashMap<String, Integer>();
    }

    public void addText(String text) {
        Matcher matcher;

        matcher = ENGLISH_WORD_PATTERN.matcher(text);

        while(matcher.find()) {
            addWord(matcher.group());
        }
    }

    public void addWord(String word) {
        Integer count;
        String rankWord;

        rankWord = word.toLowerCase();

        count = map.get(rankWord);
        if (count == null) {
            count = 1;
        } else {
            count ++;
        }

        map.put(rankWord, count);
    }

    public Map<String, Integer> getRank() {
        Map<String, Integer>  ranked;

        ranked = new TreeMap<String, Integer>(new WordRankComparator(map));
        ranked.putAll(map);

        return ranked;
    }

    public int size() {
        return map.size();
    }

    public void addAllWords(List<String> words) {
        for (String word : words) {
            addWord(word);
        }
    }
}
