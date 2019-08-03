/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题55（二）：平衡二叉树
 * // 题目：输入一棵二叉树的根结点，判断该树是不是平衡二叉树。如果某二叉树中
 * // 任意结点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 */
package datastructure.bst.offer;

import com.jchanghong.code.util.TreeNode;
import com.jchanghong.code.util.TreeUtil;
import com.jchanghong.code.util.UtilAssert;
import org.junit.Test;

import java.util.Arrays;

public class Java55_02_BalancedBinaryTree extends UtilAssert {
    public boolean binary(TreeNode head) {
        return false;
    }

    @Test
    public void test() throws Exception {
        TreeNode node = TreeUtil.construct(Arrays.asList(1, 2, 3));
        isTrue(binary(node));
        TreeNode node1 = TreeUtil.construct2(1, 2, -1, 3);
        isFalse(binary(node1));

    }
}
