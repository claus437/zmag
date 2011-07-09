package dk.clausreimer.zmag.wordrank;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;

public class IOUtilTest {

    @Test
    public void testRead() {
        InputStreamMock stream;
        byte[] expected;
        byte[] result;

        expected = "Hello".getBytes();
        stream = new InputStreamMock(new ByteArrayInputStream(expected));

        result = IOUtil.read(stream).getBytes();

        Assert.assertArrayEquals(expected, result);
        Assert.assertEquals(true, stream.isClosed());
    }

    @Test
    public void testReadCloseOnError() {
        InputStreamMock stream;
        boolean wordRankExceptionThrown;

        stream = new InputStreamMock(new ByteArrayInputStream("Hello".getBytes()));
        stream.setRaiseReadException(true);
        wordRankExceptionThrown = false;

        try {
            IOUtil.read(stream);
        } catch (WordRankException x) {
            wordRankExceptionThrown = true;
        }

        Assert.assertEquals(true, wordRankExceptionThrown);
        Assert.assertEquals(true, stream.isClosed());
    }

    @Test
    public void testCloseErrorNoExceptionThrown() {
        InputStreamMock stream;

        stream = new InputStreamMock(new ByteArrayInputStream("Hello".getBytes()));
        stream.setRaiseCloseException(true);

        IOUtil.read(stream);

        Assert.assertEquals(false, stream.isClosed());
    }

    @Test
    public void testCloseNullStream() {
        IOUtil.close(null);
    }

    @Test
    public void testReadResource() {
        String text;

        text = IOUtil.read("dk/clausreimer/zmag/wordrank/IOUtil.ReadResourceTest.txt");
        Assert.assertEquals("Hello World", text.trim());
    }

    @Test (expected = WordRankException.class)
    public void testReadResourceNotFound() {
        IOUtil.read("dk/clausreimer/zmag/wordrank/IOUtil.NoFile.txt");
    }

    @Test
    public void testReadFile() {
        File file;
        String text;

        file = new File("src/test/resources/dk/clausreimer/zmag/wordrank/IOUtil.ReadFileTest.txt");
        text = IOUtil.read(file);

        Assert.assertEquals("Hello World", text.trim());
    }

    @Test (expected = WordRankException.class)
    public void testReadFileNotFound() {
        IOUtil.read(new File("src/test/resources/dk/clausreimer/zmag/wordrank/IOUtil.ReadNoFile.txt"));
    }

}
