/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题61：扑克牌的顺子
 * // 题目：从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * // 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王可以看成任意数字。
 */
package algorithm.sort.offer;

import java.util.Arrays;

public class Java61_ContinousCards {
    public static boolean isContinuous(int [] numbers) {
        if(numbers == null || numbers.length <= 0)
            return false;
        Arrays.sort(numbers);
        int numberOf0 = 0;
        int numberOfGap = 0;
        for(int i = 0;i < numbers.length; i++){
            if(numbers[i] == 0)
                numberOf0++;
        }
        int small = numberOf0;
        int big = numberOf0 + 1;
        while(big < numbers.length){
            if(numbers[small] == numbers[big])
                return false;
            numberOfGap += numbers[big++] - numbers[small++] - 1;
        }
        if(numberOf0 >= numberOfGap)  //大于等于，而不是等于！
            return true;
        return false;
    }
}
