/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题19：正则表达式匹配
 * // 题目：请实现一个函数用来匹配包含'.'和'*'的正则表达式。模式中的字符'.'
 * // 表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题
 * // 中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"
 * // 和"ab*ac*a"匹配，但与"aa.a"及"ab*a"均不匹配。
 */
package datastructure.string.offer;

public class Java19_RegularExpressionsMatching {
    public static  boolean match(String str, String pattern) {

        return str != null && pattern != null && matchCore(str, 0, pattern, 0);

    }

    private static boolean matchCore(String str, int strPos, String pattern, int patternPos) {
        if (str.length() == strPos && pattern.length() == patternPos) {
            return true;
        }
        if (str.length() != strPos && pattern.length() == patternPos){
            return false;
        }

        if (pattern.charAt(patternPos + 1) == '*') {
            if (pattern.charAt(patternPos) == str.charAt(strPos) ||
                    (pattern.charAt(patternPos) == '.' && strPos != str.length())) {
                return matchCore(str, strPos + 1, pattern, patternPos + 2) ||
                        matchCore(str, strPos + 1, pattern, patternPos) ||
                        matchCore(str, strPos, pattern, patternPos + 2);
            } else {
                return matchCore(str, strPos, pattern, patternPos + 2);
            }
        }

        if (str.charAt(strPos) == pattern.charAt(patternPos) || (pattern.charAt(patternPos) == '.' &&
                strPos != str.length())) {
            return matchCore(str, strPos + 1, pattern, patternPos + 1);
        }

        return false;

    }

    public static void main(String[] args) {
        System.out.println("value-" + match("aaa", "a.a") + "; target-true");
        System.out.println("value-" + match("aaa", "ab*ac*a") + "; target-true");
        System.out.println("value-" + match("aaa", "aa.a") + "; target-false");
        System.out.println("value-" + match("aaa", "ab*a") + "; target-false");
    }
}
