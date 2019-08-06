/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题57（一）：和为s的两个数字
 * // 题目：输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们
 * // 的和正好是s。如果有多对数字的和等于s，输出任意一对即可。
 */
package datastructure.array.offer;

import java.util.ArrayList;

public class Java57_01_TwoNumbersWithSum {
    private static ArrayList<Integer> findNumbersEqualSum(int[] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        if (array == null || array.length <= 1) return result;
        int left = 0;
        int right = array.length - 1;
        while (left < right)  {
            int currentSum = array[left] + array[right];
            if (currentSum == sum)  {
                result.add(array[left]);
                result.add(array[right]);
                break;
            } else if (currentSum < sum) {
                left++; //增大左边较小的数
            } else {
                right--; //减小右边较大的数
            }
        }
        return result;
    }

}
