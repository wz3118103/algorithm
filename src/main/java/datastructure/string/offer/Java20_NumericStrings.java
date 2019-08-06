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


public class Java20_NumericStrings {
    private static class StrPos {
        private String str;
        private int index;

        public StrPos(String str, int index) {
            this.str = str;
            this.index = index;
        }
    }

    public static boolean isNumber(String str) {
         if (str == null) {
             return false;
         }

         StrPos strPos = new StrPos(str, 0);

         boolean numeric = scanInteger(strPos);
         if (str.charAt(strPos.index) == '.') {
             strPos.index++;
             numeric = scanUnsignedInteger(strPos) || numeric;
         }

         if (str.charAt(strPos.index) == 'e' || str.charAt(strPos.index) == 'E') {
             strPos.index++;
             numeric = numeric && scanInteger(strPos);
         }

         return numeric && strPos.index == str.length();
    }

    private static boolean scanInteger(StrPos strPos) {
        if (strPos.str.charAt(strPos.index) == '+' || strPos.str.charAt(strPos.index) == '-') {
            strPos.index++;
        }
        return scanUnsignedInteger(strPos);
    }

    private static boolean scanUnsignedInteger(StrPos strPos) {
        int before = strPos.index;
        while (strPos.index != strPos.str.length() && strPos.str.charAt(strPos.index) >= '0' &&
                strPos.str.charAt(strPos.index) <= '9') {
            strPos.index++;
        }
        return strPos.index > before;
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
