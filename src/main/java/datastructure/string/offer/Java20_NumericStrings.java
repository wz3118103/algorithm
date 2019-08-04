/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题20：表示数值的字符串
 * // 题目：请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，
 * // 字符串“+100”、“5e2”、“-123”、“3.1416”及“-1E-16”都表示数值，但“12e”、
 * // “1e3.14”、“1.2.3”、“+-5”及“12e+5.4”都不是
 */
package datastructure.string.offer;

import java.io.StringReader;
import java.util.Scanner;

public class Java20_NumericStrings {
     public static  boolean isNumber(String string) {
        Scanner scanner = new Scanner(new StringReader(string));
        int state = 0;

        while (scanner.hasNext()) {
            if (state == 0) {
                if (scanner.hasNextDouble()) {
                    state = 1;
                    scanner.nextDouble();
                } else {
                    return false;
                }
            }

            if (state == 1) {
                if (scanner.hasNextByte()) {
                    state = 0;
                    char c = (char) scanner.nextByte();
                    if (c != 'e'&&c !='E'){
                        return false;
                    }
                    continue;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("value-" + isNumber("+100") + "; target-true");
        System.out.println("value-" + isNumber("++100") + "; target-false");
        System.out.println("value-" + isNumber("5e2") + "; target-true");
        System.out.println("value-" + isNumber("3.1233") + "; target-true");
        System.out.println("value-" + isNumber("12e") + "; target-false");
        System.out.println("value-" + isNumber("11e3.13") + "; target-false");
        System.out.println("value-" + isNumber("1.2.3") + "; target-false");
    }
}
