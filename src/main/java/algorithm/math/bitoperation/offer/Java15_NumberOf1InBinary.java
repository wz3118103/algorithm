/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题15：二进制中1的个数
 * // 题目：请实现一个函数，输入一个整数，输出该数二进制表示中1的个数。例如
 * // 把9表示成二进制是1001，有2位是1。因此如果输入9，该函数输出2。
 */
package algorithm.math.bitoperation.offer;

public class Java15_NumberOf1InBinary {
    public static  int numberOf1_S1(int n) {
        int count = 0;

        while (n != 0) {
            ++count;
            n = (n - 1) & n;
        }

        return count;
    }

    public static void main(String[] args) {
        //9-1001
        //8-1000
        //7-111
        System.out.println("value-" + numberOf1_S1(9) + "; target-2");
        System.out.println("value-" + numberOf1_S1(8) + "; target-1");
        System.out.println("value-" + numberOf1_S1(7) + "; target-3");
    }
}
