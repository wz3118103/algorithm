/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题3（二）：不修改数组找出重复的数字
 * // 题目：在一个长度为n+1的数组里的所有数字都在1到n的范围内，所以数组中至
 * // 少有一个数字是重复的。请找出数组中任意一个重复的数字，但不能修改输入的
 * // 数组。例如，如果输入长度为8的数组{2, 3, 5, 4, 3, 2, 6, 7}，那么对应的
 * // 输出是重复的数字2或者3。
 * <p>
 * 思路：
 * 1）使用辅助数组，将val放到数组对应的val索引处。时间复杂度为O(n)，空间复杂度为O(n)
 * 2）使用二分法频率统计，看哪一部分超过，时间复杂度O(nlgn)，空间复杂度O(1)
 */
package algorithm.search.offer;

import java.util.Arrays;
import java.util.List;


public class Java03_02_DuplicationInArrayNoEdit {
    public static int findDuplication(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < numbers.length; ++i) {
            if (numbers[i] <= 0 || numbers[i] >= numbers.length) {
                throw new IllegalArgumentException();
            }
        }

        int start = 1;
        int end = numbers.length - 1;
        while (end >= start) {
            int middle = ((end - start) >> 1) + start;
            int count = countRange(numbers, start, middle);
            if (end == start) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }

            if (count > (middle - start + 1)) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    private static int countRange(int[] numbers, int start, int end) {
        if (numbers == null) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        for (int i = 0; i < numbers.length; ++i) {
            if (numbers[i] >= start && numbers[i] <= end) {
                ++count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] ints1 = new int[]{1, 2, 3, 4, 5, 2};
        System.out.println("value-" + findDuplication(ints1) + "; target-2");
        ints1 = new int[]{2, 3, 4, 5, 1, 1, 2, 1};
        List<Integer> list = Arrays.asList(2, 1);
        System.out.println("value-" + list.contains(findDuplication(ints1)) + "; target-true");
    }

}
