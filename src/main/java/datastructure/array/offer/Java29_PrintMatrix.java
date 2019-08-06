/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题29：顺时针打印矩阵
 * // 题目：输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 */
package datastructure.array.offer;

public class Java29_PrintMatrix {
    //元素之间没有任何其他符号
    public static void printMatrixClockwisely(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int start = 0;
        while (cols > start* 2 && rows > start * 2) {
            printMatrixInCircle(matrix, cols, rows, start);
            ++start;
        }
    }

    private static void printMatrixInCircle(int[][] matrix, int cols, int rows, int start) {
        int endX = cols - 1 - start;
        int endY = rows - 1 - start;

        for (int i = start; i <= endX; ++i) {
            printNumber(matrix[start][i]);
        }

        if (start < endY) {
            for (int i = start + 1; i < endY; ++i) {
                printNumber(matrix[i][endX]);
            }
        }

        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; --i) {
                printNumber(matrix[endY][i]);
            }
        }

        if (start < endX && start < endY - 1) {
            for (int i = endY - 1; i >= start + 1; --i) {
                printNumber(matrix[i][start]);
            }
        }
    }

    private static void printNumber(int number) {
        System.out.printf("%d\t", number);
    }

    public static void main(String[] args) {
    }
}
