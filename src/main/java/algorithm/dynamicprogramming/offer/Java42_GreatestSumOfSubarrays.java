/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题42：连续子数组的最大和
 * // 题目：输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整
 * // 数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)。
 */
package algorithm.dynamicprogramming.offer;

public class Java42_GreatestSumOfSubarrays  {

    public static int max(int[] array) {
        if (array == null || array.length <= 0) {
            throw new IllegalArgumentException();
        }
        int sum = 0;
        int max = 0;
        for (int i = 0; i < array.length; i++) {

            if(sum <= 0){      //如果当前连续n项的和小于等于0,则没必要与后面的元素相加
                sum = array[i];      //Sum重新赋值
            }else{
                sum += array[i];     //如果Sum的值大于0,则继续与后面的元素相加,
            }
            if(sum > max){         //每次改变Sum的值都有与max进行比较
                max = sum;       //如果Sum的值大于max,则将Sum的值赋值给max
            }
        }
        return max;

    }

    public static void main(String[] args) {
        int[] ints = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println("value-" + max(ints) + "; target-18");
    }
}
