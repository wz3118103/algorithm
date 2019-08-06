/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题12：矩阵中的路径
 * // 题目：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有
 * // 字符的路径。路径可以从矩阵中任意一格开始，每一步可以在矩阵中向左、右、
 * // 上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入
 * // 该格子。例如在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字
 * // 母用下划线标出）。但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个
 * // 字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 * // A B T G
 * // C F C S
 * // J D E H
 */
package algorithm.dfs_backtracking.offer;

public class Java12_StringPathInMatrix {

    public static  boolean havePath(char[][] matrix, String string) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0 ||
           string == null) {
            throw new IllegalArgumentException();
        }

        char[] str= string.toCharArray();
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                visited[i][j] = false;
            }
        }

        int pathLength = 0;
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++ col) {
                if(hasPathCore(matrix, row, col, str, pathLength, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasPathCore(char[][] matrix, int row, int col, char[] str,
                                       int pathLength, boolean[][] visited) {
        if (pathLength == str.length) {
            return true;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean hasPath = false;
        if (row >= 0 && row < rows && col >= 0 && col < cols &&
            matrix[row][col] == str[pathLength] &&
            !visited[row][col]) {
            ++pathLength;
            visited[row][col] = true;
            hasPath = hasPathCore(matrix, row, col - 1, str, pathLength, visited) ||
                    hasPathCore(matrix, row, col + 1, str, pathLength, visited) ||
                    hasPathCore(matrix, row - 1, col, str, pathLength, visited) ||
                    hasPathCore(matrix, row + 1, col, str, pathLength, visited);
            if (!hasPath) {
                --pathLength;
                visited[row][col] = false;
            }
        }
        return hasPath;
    }

    public static void main(String[] args) {
        char[][] chars = {{'a', 'b', 't', 'g'},
                {'c', 'f', 'c', 's'},
                {'j', 'd', 'e', 'h'}
        };

        System.out.println("value-" + havePath(chars, "bfce") + "; target-true");
        System.out.println("value-" + havePath(chars, "abfb") + "; target-false");
    }
}
