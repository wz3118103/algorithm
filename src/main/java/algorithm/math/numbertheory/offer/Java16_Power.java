/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题16：数值的整数次方
 * // 题目：实现函数double Power(double base, int exponent)，求base的exponent
 * // 次方。不得使用库函数，同时不需要考虑大数问题。
 */
package algorithm.math.numbertheory.offer;

public class Java16_Power {

    public static  double power(double base, int exponent) {
        if (equels(base, 0.0) && exponent < 0) {
            throw new IllegalArgumentException();
        }
        int absExponent = Math.abs(exponent);
        double result = powerWithUnsignedExponent(base, absExponent);
        if (exponent < 0) {
            result = 1.0 / result;
        }

        return result;
    }

    private static double powerWithUnsignedExponent(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;

        double result = powerWithUnsignedExponent(base, exponent >> 1);
        result *= result;
        if ((exponent & 0x1) == 1)
            result *= base;

        return result;
    }

    public static  boolean equels(double d1, double d2) {
        return Math.abs(d1 - d2) < 0.0000000000001;
    }

    public static void main(String[] args) {
        System.out.println("value-" + power(2.3, 4) + "; target-" + Math.pow(2.3, 4));
    }
}
