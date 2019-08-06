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
    public void findNumsAppearOnce(int[] array,int[] num1 , int[] num2) {
        if (array == null || array.length < 2) {
            return;
        }
        num1[0] = 0;
        num2[0] = 0;
        int number = array[0];
        for (int i = 1; i < array.length; i++) {
            number ^= array[i];
        }
        // 异或后的数1出现在第几位
        int index = 0;
        while ((number & 1) == 0) {
            number = number >> 1;
            index++;
        }

        for (int i = 0; i < array.length; i++) {
            // 判断第index位是不是0
            boolean isBit = ((array[i] >> index) & 1) == 0;
            if (isBit) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }

    public static void main(String[] args) {
    }
}
