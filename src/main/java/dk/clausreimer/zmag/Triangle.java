package dk.clausreimer.zmag;


import java.util.Arrays;

public class Triangle {
    public static final int SCALENE = 1;
    public static final int ISOSCELES = 2;
    public static final int EQUILATERAL = 3;
    public static final int ERROR = 4;

    public static int getType(int a, int b, int c) {
        int[] lengths;
        int equalSides;

        equalSides = ERROR;

        lengths = new int[]{a, b, c};
        Arrays.sort(lengths);

        if (isLengthsPositive(lengths) && isTriangle(lengths)) {
            equalSides = 1;

            for (int i = 1; i < lengths.length; i++) {
                if (lengths[i - 1] == lengths[i]) {
                    equalSides++;
                }
            }
        }

        return equalSides;
    }

    private static boolean isTriangle(int[] sortedLengths) {
        return sortedLengths[0] + sortedLengths[1] > sortedLengths[2];
    }

    private static boolean isLengthsPositive(int[] lengths) {
        boolean positive;

        positive = true;

        for (int length : lengths) {
            if (length <= 0) {
                positive = false;
                break;
            }
        }

        return positive;
    }
}
