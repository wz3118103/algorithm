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
    public static int getSum(int n) {
        int sum = n;
        boolean flag = (n > 1) && ((sum += getSum(n-1)) > 0);
        //上面这句话相当于：
        //if(n > 1)
        //   sum += getSum(n - 1);

        //也可以使用||来实现
        //boolean flag = (n == 1) || ((sum += getSum(n - 1)) > 0);
        return sum;
    }
}
