/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题57（二）：为s的连续正数序列
 * // 题目：输入一个正数s，打印出所有和为s的连续正数序列（至少含有两个数）。
 * // 例如输入15，由于1+2+3+4+5=4+5+6=7+8=15，所以结果打印出3个连续序列1～5、
 * // 4～6和7～8。
 */
package datastructure.array.offer;

import java.util.ArrayList;

public class Java57_02_ContinuousSquenceWithSum {
    public static ArrayList<ArrayList<Integer>> findSequenceEqualSum(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (sum < 3)  {
            return result;
        }
        int min = 1;
        int max = 2;
        //因为小数被舍去，所以sum+1
        int middle = (sum + 1) / 2;
        int currentSum = min + max;
        //至少2个数，所以最小数要小于sum的一半
        while (min < middle)  {
            if (currentSum == sum) {
                result.add(getContinuousSequence(min, max));
            }
            //减少序列数目
            while (currentSum > sum && min < middle) {
                currentSum -= min;
                //更新最小数
                min++;
                if (currentSum == sum)       {
                    result.add(getContinuousSequence(min, max));
                }
            }
            //增多序列数目
            max++;
            currentSum += max;
        }
        return result;
    }


    private static ArrayList<Integer> getContinuousSequence(int begin, int end)  {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = begin; i <= end; i++)  {
            result.add(i);
        }
        return result;
    }

}
