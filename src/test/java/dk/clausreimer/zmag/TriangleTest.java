package dk.clausreimer.zmag;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: claus
 * Date: 08-07-11
 * Time: 18:34
 * To change this template use File | Settings | File Templates.
 */
public class TriangleTest {

    @Test
    public void testCreate() {
        new Triangle();
    }

    @Test
    public void testScalene() {
        Assert.assertEquals(Triangle.SCALENE, Triangle.getType(20, 11, 10));
        Assert.assertEquals(Triangle.SCALENE, Triangle.getType(10, 20, 11));
        Assert.assertEquals(Triangle.SCALENE, Triangle.getType(11, 10, 20));
    }

    @Test
    public void testIsosceles() {
        Assert.assertEquals(Triangle.ISOSCELES, Triangle.getType(11, 10, 10));
        Assert.assertEquals(Triangle.ISOSCELES, Triangle.getType(10, 11, 10));
        Assert.assertEquals(Triangle.ISOSCELES, Triangle.getType(10, 10, 11));
    }

    @Test
    public void testEquilateral() {
        Assert.assertEquals(Triangle.EQUILATERAL, Triangle.getType(10, 10, 10));
    }

    @Test
    public void testErrorNoLength() {
        Assert.assertEquals(Triangle.ERROR, Triangle.getType(0, 10, 10));
        Assert.assertEquals(Triangle.ERROR, Triangle.getType(10, 0, 10));
        Assert.assertEquals(Triangle.ERROR, Triangle.getType(10, 10, 0));

        Assert.assertEquals(Triangle.ERROR, Triangle.getType(10, 0, 0));
        Assert.assertEquals(Triangle.ERROR, Triangle.getType(0, 10, 0));
        Assert.assertEquals(Triangle.ERROR, Triangle.getType(0, 0, 10));

        Assert.assertEquals(Triangle.ERROR, Triangle.getType(0, 0, 0));
    }

    @Test
    public void testErrorBadLengths() {
        Assert.assertEquals(Triangle.ERROR, Triangle.getType(10, 5, 5));
        Assert.assertEquals(Triangle.ERROR, Triangle.getType(5, 10, 5));
        Assert.assertEquals(Triangle.ERROR, Triangle.getType(10, 5, 5));

        Assert.assertEquals(Triangle.ERROR, Triangle.getType(10, 4, 4));
        Assert.assertEquals(Triangle.ERROR, Triangle.getType(4, 10, 4));
        Assert.assertEquals(Triangle.ERROR, Triangle.getType(10, 4, 4));

    }
}
