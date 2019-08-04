/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题48：最长不含重复字符的子字符串
 * // 题目：请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子
 * // 字符串的长度。假设字符串中只包含从'a'到'z'的字符。
 */
package algorithm.dynamicprogramming.offer;

public class Java48_LongestSubstringWithoutDup  {
    public static String sub(String text) {
        return text;
    }

    public static void main(String[] args) {
        System.out.println("value-" + sub("abc") + "; target-abc");
        System.out.println("value-" + sub("abcbc") + "; target-abc");
        System.out.println("value-" + sub("aabcdff") + "; target-abcdf");
    }
}
