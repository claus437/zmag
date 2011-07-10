package dk.clausreimer.zmag.wordrank;

import org.junit.Test;

import org.junit.Assert;
import java.util.Arrays;
import java.util.Map;


public class WordRankTest {
    private WordRank wordRank = new WordRank();


    @Test
    public void testAddWord() {
        wordRank.addWord("hello");
        Assert.assertEquals(1, wordRank.size());
    }

    @Test
    public void testAddAllWords() {
        wordRank.addAllWords(Arrays.asList("hello", "java", "world", "java", "world", "java"));
        Assert.assertEquals(3, wordRank.size());
    }

    @Test
    public void testGetTopRatings() {
        Map<String, Integer> rank;

        wordRank.addAllWords(Arrays.asList("hello", "java", "world", "java", "world", "java"));
        rank = wordRank.getRank();

        Assert.assertEquals(3, rank.get("java").intValue());
        Assert.assertEquals(2, rank.get("world").intValue());
        Assert.assertEquals(1, rank.get("hello").intValue());
        Assert.assertArrayEquals(new String[]{"java", "world", "hello"}, rank.keySet().toArray());
    }

    @Test
    public void testAddSimpleText() {
        Map<String, Integer> rank;

        wordRank.addText("hello world world");
        rank = wordRank.getRank();

        Assert.assertEquals(2, rank.get("world").intValue());
        Assert.assertEquals(1, rank.get("hello").intValue());

        Assert.assertArrayEquals(new String[]{"world", "hello"}, rank.keySet().toArray());
    }

    @Test
    public void testAddTextWithNonLetters() {
        Map<String, Integer> rank;

        wordRank.addText("hello,hello.hello\njava?java:world");
        rank = wordRank.getRank();

        Assert.assertEquals(3, rank.get("hello").intValue());
        Assert.assertEquals(2, rank.get("java").intValue());
        Assert.assertEquals(1, rank.get("world").intValue());

        Assert.assertArrayEquals(new String[]{"hello", "java", "world"}, rank.keySet().toArray());
    }

    @Test
    public void testCaseSensitivity() {
        Map<String, Integer> rank;

        wordRank.addText("Hello hello World");
        rank = wordRank.getRank();

        Assert.assertEquals(2, rank.get("hello").intValue());
        Assert.assertEquals(1, rank.get("world").intValue());

        Assert.assertArrayEquals(new String[]{"hello", "world"}, rank.keySet().toArray());
    }

    @Test
    public void testMain() {
        Main.main(new String[]{});
    }
}

