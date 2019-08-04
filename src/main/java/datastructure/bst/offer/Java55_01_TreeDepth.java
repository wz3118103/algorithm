/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题55（一）：二叉树的深度
 * // 题目：输入一棵二叉树的根结点，求该树的深度。从根结点到叶结点依次经过的
 * // 结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 */
package datastructure.bst.offer;

import java.util.Arrays;

public class Java55_01_TreeDepth {
    public static int treeDepth(TreeNode head) {
        return -1;
    }

    public static void main(String[] args) {
        TreeNode node = TreeUtil.construct(Arrays.asList(1, 2, 3));
        System.out.println("value-" + treeDepth(node) + "; target-2");
        node = TreeUtil.construct(Arrays.asList(1, 2, 3, 4));
        System.out.println("value-" + treeDepth(node) + "; target-3");
    }
}
