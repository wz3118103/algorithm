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

import org.junit.Assert;
import org.junit.Test;

public class Java12_StringPathInMatrix {

    public boolean havePath(char[][] chars, String string) {
        return false;
    }

    @Test
    public void test() throws Exception {
        char[][] chars = {{'a', 'b', 't', 'g'},
                {'c', 'f', 'c', 's'},
                {'j', 'd', 'e', 'h'}
        };
        Assert.assertEquals(havePath(chars, "bfce"), true);
        Assert.assertEquals(havePath(chars, "abfb"), false);
    }
}
