/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题40：最小的k个数
 * // 题目：输入n个整数，找出其中最小的k个数。例如输入4、5、1、6、2、7、3、8
 * // 这8个数字，则最小的4个数字是1、2、3、4。
 *
 * 1）parition
 * 2）堆，构建一个最大堆
 */
package datastructure.queueandheap.offer;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class Java40_KLeastNumbers  {
    public static int[] kth_s1(int[] nums, int k) {
        if (nums == null || nums.length <= 0 || k > nums.length || k <= 0) {
            throw new IllegalArgumentException();
        }

        int start = 0;
        int end = nums.length - 1;
        int index = partition(nums, start, end);
        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
                index = partition(nums, start, end);
            } else {
                start = index + 1;
                index = partition(nums, start, end);
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; ++i) {
            result[i] = nums[i];
        }
        return result;
    }

    private static int partition(int[] nums, int start, int end) {
        if (nums == null || nums.length <= 0 || start < 0 || end >= nums.length) {
            throw new IllegalArgumentException();
        }
        int index = new Random().nextInt(end - start) + start;
        swap(nums, index, end);

        int small = start - 1;
        for (index = start; start < end; ++index) {
            if (nums[index] < nums[end]) {
                ++small;
                if (small != index) {
                    swap(nums, index, small);
                }
            }
        }
        ++small;
        swap(nums, small, end);
        return small;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }



    public static int[] kth_s2(int[] nums, int k) {
        if (nums == null || nums.length <= 0 || k > nums.length || k <= 0) {
            throw new IllegalArgumentException();
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);

        for (int i = 0; i < nums.length; ++i) {
            if (maxHeap.size() <= k) {
                maxHeap.add(nums[i]);
            } else {
                if (nums[i] < maxHeap.peek()) {
                    maxHeap.remove(maxHeap.peek());
                    maxHeap.add(nums[i]);
                }
            }
        }

        int[] result = new int[k];
        int i = 0;
        for (int element : maxHeap) {
            result[i++] = element;
        }
        return result;
    }

    public static void main(String[] args) {
    }
}
