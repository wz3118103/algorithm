/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题53（二）：0到n-1中缺失的数字
 * // 题目：一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字
 * // 都在范围0到n-1之内。在范围0到n-1的n个数字中有且只有一个数字不在该数组
 * // 中，请找出这个数字。
 */
package algorithm.search.offer;


public class Java53_02_MissingNumber {
    public static int getMissingNumber(int[] array){
        if (array == null || array.length == 0) {
            return -1;
        }
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (mid >= 0 && array[mid] != mid) {
                if (mid == 0 || array[mid - 1] == mid - 1) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            } else if (array[mid] == mid) {
                start = mid + 1;
            }
        }
        if (start == array.length)
            return array.length;
        return -1;
    }


    public static void main(String[] args) {
        int[] ints = {0, 1, 2, 4};
        System.out.println("value-" + getMissingNumber(ints) + "; target-3");
    }
}
