/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题54：二叉搜索树的第k个结点
 * // 题目：给定一棵二叉搜索树，请找出其中的第k大的结点。
 */
package datastructure.bst.offer;

import java.util.Arrays;

public class Java54_KthNodeInBST {
    public static TreeNode kth(TreeNode head, int k) {
        return null;
    }

    public static void main(String[] args) {
        TreeNode head = TreeUtil.construct(Arrays.asList(2, 1, 3));
        System.out.println("value-" + kth(head, 1) == head.left + "; target-true");
        System.out.println("value-" + kth(head, 2) == head + "; target-true");
        System.out.println("value-" + kth(head, 3) == head.right + "; target-true");
    }
}