package dk.clausreimer.zmag.wordrank;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class IOUtil {

    private IOUtil() {
    }

    public static String read(File file) {
        InputStream stream;
        String text;

        try {
            stream = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException x) {
            throw new WordRankException("file " + file.getAbsolutePath() + " not found", x);
        }

        text = read(stream);

        return text;
    }

    public static String read(String resource) {
        InputStream stream;
        String text;

        stream = IOUtil.class.getClassLoader().getResourceAsStream(resource);
        if (stream == null) {
            throw new WordRankException("resource " + resource + " not found");
        }

        text = IOUtil.read(stream);

        return text;
    }

    public static String read(InputStream stream) {
        Reader reader;
        StringBuffer content;
        char[] data;
        int length;

        reader = new BufferedReader(new InputStreamReader(stream));
        data = new char[1024];
        content = new StringBuffer();

        try {
            while ((length = reader.read(data)) != -1) {
                content.append(data, 0, length);
            }
        } catch (IOException x) {
            throw new WordRankException("failed reading data file, " + x.getMessage(), x);
        } finally {
            close(stream);
        }

        return content.toString();
    }

    public static void close(Closeable stream) {
        if (stream == null) {
            return;
        }

        try {
            stream.close();
        } catch (IOException x) {
            System.err.println("failed closing stream object " + stream);
        }
    }

}
