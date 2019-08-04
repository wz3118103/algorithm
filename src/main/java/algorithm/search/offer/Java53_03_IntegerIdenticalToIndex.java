/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题53（三）：数组中数值和下标相等的元素
 * // 题目：假设一个单调递增的数组里的每个元素都是整数并且是唯一的。请编程实
 * // 现一个函数找出数组中任意一个数值等于其下标的元素。例如，在数组{-3, -1,
 * // 1, 3, 5}中，数字3和它的下标相等。
 */
package algorithm.search.offer;

import java.util.HashSet;
import java.util.Set;

public class Java53_03_IntegerIdenticalToIndex{
    public static Set<Integer> numbers(int[] ints) {
        Set<Integer> number = new HashSet<>();
        return number;
    }

    public static void main(String[] args) {
        Set<Integer> integers = numbers(new int[]{-3, -1, 1, 3, 5});
        System.out.println("value-" + integers.contains(3) + "; target-true");
    }
}
