/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题63：股票的最大利润
 * // 题目：假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖交易该股
 * // 票可能获得的利润是多少？例如一只股票在某些时间节点的价格为{9, 11, 8, 5,
 * // 7, 12, 16, 14}。如果我们能在价格为5的时候买入并在价格为16时卖出，则能
 * // 收获最大的利润11。
 */
package datastructure.array.offer;

public class Java63_MaximalProfit {
    public static int MaxDiff(int[] arr) {
        if(arr == null || arr.length < 2)
            return -1;  //error
        int min = arr[0];

        //最大利润可以是负数，只要亏损最小就行
        int maxDiff = arr[1] - min;
        for(int i = 1;i < arr.length; i++) {
            if(arr[i - 1] < min)     //保存“之前”最小数字
                min = arr[i - 1];
            if(arr[i] - min > maxDiff)
                maxDiff = arr[i] - min;
        }
        return maxDiff;
    }
}
