/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题40：最小的k个数
 * // 题目：输入n个整数，找出其中最小的k个数。例如输入4、5、1、6、2、7、3、8
 * // 这8个数字，则最小的4个数字是1、2、3、4。
 */
package datastructure.queueandheap.offer;

import java.util.Arrays;

public class Java40_KLeastNumbers  {
    public static int[] kth(int[] ints, int k) {
        return null;
    }

    public static void main(String[] args) {
        int[] ints = {4, 5, 1, 6, 2, 7, 3, 8};
        System.out.println("value-" + kth(ints, 4).length + "; target-4");
        int[] k = kth(ints, 4);
        Arrays.sort(k);
        System.out.println("value-" + Arrays.equals(k, new int[]{1, 2, 3, 4}) + "; target-true");
    }
}
