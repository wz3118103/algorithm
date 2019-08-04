/**
 * package com.jchanghong.code;
 * // 面试题3（一）：找出数组中重复的数字
 * // 题目：在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * // 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2, 3, 1, 0, 2, 5, 3}，
 * // 那么对应的输出是重复的数字2或者3。
 */
package datastructure.array.offer;

import java.util.Arrays;
import java.util.List;

public class Java03_01_DuplicationInArray {

    public static int findDuplication(int[] ints) {

        return 0;
    }

    public static void main(String[] args) {
        int[] ints1 = new int[]{1, 2, 3, 4, 5, 2};
        System.out.println("value-" + findDuplication(ints1) + "; target-2");
        ints1 = new int[]{2, 3, 4, 5, 1, 1, 2, 1};
        List<Integer> list = Arrays.asList(2, 1);
        System.out.println("value-" + list.contains(findDuplication(ints1)) + "; target-true");
    }
}
