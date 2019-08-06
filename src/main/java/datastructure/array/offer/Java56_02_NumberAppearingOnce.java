/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题56（二）：数组中唯一只出现一次的数字
 * // 题目：在一个数组中除了一个数字只出现一次之外，其他数字都出现了三次。请
 * // 找出那个只出现一次的数字。
 */
package datastructure.array.offer;

public class Java56_02_NumberAppearingOnce {
    public static int findNumberAppearOnce(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException();
        }

        int[] bitSum = new int[32];
        for (int num : array) {
            // 掩码
            int bitMask = 1;
            // 从右向左判断位数是否为1
            for (int i = bitSum.length - 1; i >= 0; i--)  {
                int bit = num & bitMask;
                //位数为1
                if (bit != 0)  {
                    bitSum[i] += 1;
                }
                bitMask = bitMask << 1;
            }
        }

        int result = 0;
        for (int i = 0; i < bitSum.length; i++) {
            result = result << 1;
            // 要么为0要么为1
            result += bitSum[i] % 3;
        }
        return result;
    }

    public static void main(String[] args) {
    }
}
