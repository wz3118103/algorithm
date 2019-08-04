/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题56（一）：数组中只出现一次的两个数字
 * // 题目：一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序
 * // 找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 */
package datastructure.array.offer;

import java.util.List;

public class Java56_01_NumbersAppearOnce {
    public static List<Integer> two(int[] ints) {
        return null;
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 2, 3};
        List<Integer> list = two(ints);
        System.out.println("value-" + list.size() + "; target-2");
        System.out.println("value-" + list.contains(1) + "; target-true");
        System.out.println("value-" + list.contains(4) + "; target-true");

    }
}
