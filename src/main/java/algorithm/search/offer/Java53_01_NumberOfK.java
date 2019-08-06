/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题53（一）：数字在排序数组中出现的次数
 * // 题目：统计一个数字在排序数组中出现的次数。例如输入排序数组{1, 2, 3, 3,
 * // 3, 3, 4, 5}和数字3，由于3在这个数组中出现了4次，因此输出4。
 */
package algorithm.search.offer;


import java.util.Arrays;

public class Java53_01_NumberOfK {
    public static int getNumberOfK(int[] array, int k) {
        int result=0;
        int mid = array.length/2;

        if(array==null || array.length == 0)
            return 0;
        if(array.length == 1) {
            if(array[0] == k)
                return 1;
            else
                return 0;
        }

        if(k < array[mid])
            result += getNumberOfK(Arrays.copyOfRange(array, 0, mid),k);
        else if(k > array[mid])
            result += getNumberOfK(Arrays.copyOfRange(array, mid, array.length),k);
        else {
            for(int i = mid;i < array.length;i++) {
                if(array[i] == k)
                    result++;
                else
                    break;
            }

            for(int i = mid - 1;i >= 0;i--) {
                if(array[i] == k)
                    result++;
                else
                    break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 3, 3, 3, 4, 5};
        System.out.println("value-" + getNumberOfK(ints, 3) +"; target-4");
    }
}
