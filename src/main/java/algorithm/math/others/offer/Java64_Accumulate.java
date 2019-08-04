/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题64：求1+2+…+n
 * // 题目：求1+2+…+n，要求不能使用乘除法、for、while、if、else、switch、case
 * // 等关键字及条件判断语句（A?B:C）。
 */
package algorithm.math.others.offer;

public class Java64_Accumulate {
    public static  int sum(int n) {
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(sumtest(10));
        System.out.println(sumtest(100));
        Const.integerList.forEach(a ->  System.out.println("value-" + sumtest(a) + "; target-" + sum(a)));
    }

    public static int sumtest(int n) {
        return (1 + n) * n / 2;
    }
}
