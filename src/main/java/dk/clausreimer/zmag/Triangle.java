package dk.clausreimer.zmag;


public class Triangle {
    public static final int SCALENE = 1;
    public static final int ISOSCELES = 2;
    public static final int EQUILATERAL = 3;
    public static final int ERROR = 4;

    public static int getType(int a, int b, int c) {
        if (!isTriangle(a, b, c)) {
            return ERROR;
        }


        if (a == b && b == c) {
            return EQUILATERAL;
        }

        if (a == b || b == c || a == c) {
            return ISOSCELES;
        }


        return SCALENE;
    }

    private static boolean isTriangle(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return false;
        }

        if (isDimonsionsFine(a, b, c)) {
            return true;
        }

        if (isDimonsionsFine(b, a, c)) {
            return true;
        }

        if (isDimonsionsFine(c, a, b)) {
            return true;
        }

        return false;
    }

    private static boolean isDimonsionsFine(int baseline, int left, int right) {
        return baseline >= left && baseline >= right && left + right > baseline;
    }

}
