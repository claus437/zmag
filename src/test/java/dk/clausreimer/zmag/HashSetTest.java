package dk.clausreimer.zmag;

import org.junit.Test;

import junit.framework.Assert;
import java.util.Arrays;
import java.util.Iterator;


public class HashSetTest {
    private HashSet<String> list = new HashSet<String>();

    @Test
    public void testSize() {
        Assert.assertEquals(0, list.size());

        list.add("Hello");
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertEquals(true, list.isEmpty());

        list.add("Hello");
        Assert.assertEquals(false, list.isEmpty());
    }

    @Test
    public void testContains() {
        list.add("Hello");

        Assert.assertEquals(false, list.contains("hello"));
        Assert.assertEquals(true, list.contains("Hello"));
    }

    @Test
    public void testIterator() {
        Iterator<String> iterator;
        String value;

        iterator = list.iterator();
        Assert.assertEquals(false, iterator.hasNext());

        list.add("Hello");
        list.add("World");

        iterator = list.iterator();

        Assert.assertEquals(true, iterator.hasNext());
        value = iterator.next();
        Assert.assertTrue(value.equals("Hello") || value.equals("World"));

        Assert.assertEquals(true, iterator.hasNext());
        value = iterator.next();
        Assert.assertTrue(value.equals("Hello") || value.equals("World"));

        Assert.assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testToArrayNew() {
        Object[] values;

        values = list.toArray();
        Assert.assertEquals(0, values.length);

        list.add("Hello");
        values = list.toArray();
        Assert.assertEquals(1, values.length);
        Assert.assertEquals("Hello", values[0]);
    }

    @Test
    public void testToArrayCopy() {
        String[] values;

        values = new String[0];

        list.toArray(values);
        Assert.assertEquals(0, values.length);

        list.add("Hello");
        values = new String[1];

        list.toArray(values);
        Assert.assertEquals(1, values.length);
        Assert.assertEquals("Hello", values[0]);
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(true, list.add("Hello"));
        Assert.assertEquals(false, list.add("Hello"));
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testRemove() {
        list.add("Hello");

        Assert.assertEquals(false, list.remove("World"));
        Assert.assertEquals(true, list.remove("Hello"));
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testContainsAll() {
        String[] values;

        values = new String[]{"Hello", "World"};

        list.add(values[0]);
        Assert.assertEquals(false, list.containsAll(Arrays.asList(values)));

        list.add(values[1]);
        Assert.assertEquals(true, list.containsAll(Arrays.asList(values)));
    }

    @Test
    public void testAddAll() {
        String[] values;

        values = new String[]{"Hello", "World"};

        Assert.assertEquals(true, list.addAll(Arrays.asList(values)));
        Assert.assertEquals(false, list.addAll(Arrays.asList(values)));
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void testRetainAll() {
        list.addAll(Arrays.asList("Hello", "Java", "World"));

        Assert.assertEquals(true, list.retainAll(Arrays.asList("Java")));
        Assert.assertEquals(1, list.size());

        Assert.assertEquals(false, list.retainAll(Arrays.asList("Java")));
        Assert.assertEquals(1, list.size());
        Assert.assertEquals("Java", list.iterator().next());
    }

    @Test
    public void testRemoveAll() {
        list.addAll(Arrays.asList("Hello", "Java", "World"));

        Assert.assertEquals(true, list.removeAll(Arrays.asList("Hello", "World")));
        Assert.assertEquals(1, list.size());
        Assert.assertEquals("Java", list.iterator().next());

        Assert.assertEquals(false, list.removeAll(Arrays.asList("Hello", "World")));
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testClear() {
        list.addAll(Arrays.asList("Hello", "World"));
        list.clear();

        Assert.assertEquals(0, list.size());
    }
}
