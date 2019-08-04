/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题50（一）：字符串中第一个只出现一次的字符
 * // 题目：在字符串中找出第一个只出现一次的字符。如输入"abaccdeff"，则输出
 * // 'b'。
 */
package datastructure.string.offer;

public class Java50_01_FirstNotRepeatingChar {

    public static char first(String text) {
        return 'f';
    }

    public static void main(String[] args) {
        System.out.println("value-" + first("abaccdeff") + "; target-b");
        System.out.println("value-" + first("aabbvf") + "; target-v");
    }
}
