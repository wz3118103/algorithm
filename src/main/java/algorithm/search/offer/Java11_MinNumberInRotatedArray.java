/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题11：旋转数组的最小数字
 * // 题目：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * // 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如数组
 * // {3, 4, 5, 1, 2}为{1, 2, 3, 4, 5}的一个旋转，该数组的最小值为1。
 */
package algorithm.search.offer;

public class Java11_MinNumberInRotatedArray {
    public static int min(int[] array) {
        if(array == null || array.length <= 0){
            throw new IllegalArgumentException();
        }
        int left = 0;
        int right = array.length - 1;
        int mid = 0;

        while (array[left] >= array[right]) {
            if (right - left == 1) {
                mid = right;
                break;
            }
            mid = ((right - left) >> 2) + left;
            if (array[left] == array[right] && array[mid] == array[left]) {
                return minInorder(array, left, right);
            }
            if (array[mid] >= array[left]) {
                left = mid;
            } else if (array[mid] <= array[right]) {
                right = mid;
            }
        }
        return array[mid];
    }

    private static int minInorder(int[] array, int left, int right) {
        int result = array[left];
        for (int i = left + 1; i <= right; ++i) {
            if (result > array[i]) {
                result = array[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{3, 4, 5, 1, 2};
        System.out.println("value-" + min(ints) +"; target-1");
        ints = new int[]{3, 4, 5, 0, 1, 2};
        System.out.println("value-" + min(ints) +"; target-0");
    }
}
