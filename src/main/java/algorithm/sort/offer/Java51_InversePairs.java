/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题51：数组中的逆序对
 * // 题目：在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组
 * // 成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 */
package algorithm.sort.offer;

public class Java51_InversePairs{
    public static int inversePairs(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        //定义辅助数组
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        //数值过大求余
        int count = inversePairsCore(array, copy, 0, array.length - 1);

        //返回结果
        return count;

    }

    private static int inversePairsCore(int[] array, int[] copy, int low, int high) {
        if (low == high) {
            return 0;
        }
        int mid = (low + high) >> 1;
        int leftCount = inversePairsCore(array, copy, low, mid) % 1000000007;
        int rightCount = inversePairsCore(array, copy, mid + 1, high) % 1000000007;

        int count = 0;
        int i = mid;
        int j = high;
        int locCopy = high;

        while (i >= low && j > mid) {
            if (array[i] > array[j]) {
                count += j - mid;
                copy[locCopy--] = array[i--];
                if (count >= 1000000007)//数值过大求余
                {
                    count %= 1000000007;
                }
            } else {
                copy[locCopy--] = array[j--];
            }
        }

        for (; i >= low; i--) {
            copy[locCopy--] = array[i];
        }

        for (; j > mid; j--) {
            copy[locCopy--] = array[j];
        }

        for (int s = low; s <= high; s++) {
            array[s] = copy[s];
        }

        return (leftCount + rightCount + count) % 1000000007;
    }

    public static void main(String[] args) {
        int[] ints = {7, 5, 6, 4};
        System.out.println("value-" + inversePairs(ints) + "; target-5");
    }
}
