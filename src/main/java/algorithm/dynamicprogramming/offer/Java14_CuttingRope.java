/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题14：剪绳子
 * // 题目：给你一根长度为n绳子，请把绳子剪成m段（m、n都是整数，n>1并且m≥1）。
 * // 每段的绳子的长度记为k[0]、k[1]、……、k[m]。k[0]*k[1]*…*k[m]可能的最大乘
 * // 积是多少？例如当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此
 * // 时得到最大的乘积18。
 */
package algorithm.dynamicprogramming.offer;

public class Java14_CuttingRope {
    //-1表示参数错误
    public static  int max(int n) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("value-" + max(0) + "; target:-1");
        System.out.println("value-" + max(-1) + "; target:-1");
        System.out.println("value-" + max(8) + "; target:18");

    }
}