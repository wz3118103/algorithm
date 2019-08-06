/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题38：字符串的排列
 * // 题目：输入一个字符串，打印出该字符串中字符的所有排列。例如输入字符串abc，
 * // 则打印出由字符a、b、c所能排列出来的所有字符串abc、acb、bac、bca、cab和cba。
 */
package algorithm.dfs_backtracking.offer;

import java.util.HashSet;
import java.util.Set;

public class Java38_StringPermutation {
    public static Set<String> permutation(String text) {
        Set<String> strings = new HashSet<>();
        permutationCore(text.toCharArray(), 0, strings);
        return strings;
    }

    private static void permutationCore(char[] text, int index, Set<String> result){
        if (index == text.length) {
            result.add(text.toString());
            return;
        }

        for (int i = index; i != text.length; ++i) {
            swap(text, index, i);
            permutationCore(text, index + 1, result);
            swap(text, index, i);
        }
    }

    private static void swap(char[] txt, int i, int j) {
        char tmp = txt[i];
        txt[i] = txt[j];
        txt[j] = tmp;
    }

    public static void main(String[] args) {
    }
}
