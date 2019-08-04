/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题3（二）：不修改数组找出重复的数字
 * // 题目：在一个长度为n+1的数组里的所有数字都在1到n的范围内，所以数组中至
 * // 少有一个数字是重复的。请找出数组中任意一个重复的数字，但不能修改输入的
 * // 数组。例如，如果输入长度为8的数组{2, 3, 5, 4, 3, 2, 6, 7}，那么对应的
 * // 输出是重复的数字2或者3。
 */
package datastructure.array.offer;

import java.util.Arrays;
import java.util.List;


public class Java03_02_DuplicationInArrayNoEdit {
    public static int findDuplication(int[] ints) {
        return 0;
    }

    public static void main(String[] args) {
        int[] ints1 = new int[]{1, 2, 3, 4, 5, 2};
        System.out.println("value-" + findDuplication(ints1) + "; target-2");
        ints1 = new int[]{2, 3, 4, 5, 1, 1, 2, 1};
        List<Integer> list = Arrays.asList(2, 1);
        System.out.println("value-" + list.contains(findDuplication(ints1)) + "; target-true");
    }

}
