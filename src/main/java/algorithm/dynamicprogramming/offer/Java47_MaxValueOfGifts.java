/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题47：礼物的最大价值
 * // 题目：在一个m×n的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值
 * // （价值大于0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向左或
 * // 者向下移动一格直到到达棋盘的右下角。给定一个棋盘及其上面的礼物，请计
 * // 算你最多能拿到多少价值的礼物？
 */
package algorithm.dynamicprogramming.offer;

public class Java47_MaxValueOfGifts  {
    public static int max(int[][] gift) {
        return 0;
    }

    public static void main(String[] args) {
        int[][] ints = {
                {1, 10, 3, 8},
                {12, 2, 9, 6},
                {5, 7, 4, 11}
                ,
                {3, 7, 16, 5}
        };
        System.out.println("value-" + max(ints) + "; target-15");
    }
}
