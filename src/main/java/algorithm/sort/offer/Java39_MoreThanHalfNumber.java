/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题39：数组中出现次数超过一半的数字
 * // 题目：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例
 * // 如输入一个长度为9的数组{1, 2, 3, 2, 2, 2, 5, 4, 2}。由于数字2在数组中
 * // 出现了5次，超过数组长度的一半，因此输出2。
 *
 * 思路：
 * 1）基于partition，返回中间的值
 * 2）基于正反特性
 */
package algorithm.sort.offer;

public class Java39_MoreThanHalfNumber {
    public static int half(int[] nums) {
        if (nums == null || nums.length <= 0) {
            throw new IllegalArgumentException();
        }

        int result = nums[0];
        int times = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (times == 0) {
                result = nums[i];
                times = 1;
            } else if (nums[i] == result) {
                times++;
            } else {
                times--;
            }
        }

        if (!check(nums, result)) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    private static boolean check(int[] nums, int result) {
        int times = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == result) {
                times++;
            }
        }

        boolean isMoreThanHalf = true;
        if (times * 2 <= nums.length) {
            isMoreThanHalf = false;
        }
        return isMoreThanHalf;
    }

    public static void main(String[] args) {
        System.out.println("value-" + half(new int[]{1, 2, 3, 4}) + "; target:-1");
        int[] ints = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println("value-" + half(ints) + "; target-2");
    }
}
