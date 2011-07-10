package dk.clausreimer.zmag;

import org.junit.Test;

import junit.framework.Assert;


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
