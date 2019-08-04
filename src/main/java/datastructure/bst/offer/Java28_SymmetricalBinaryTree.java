/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题28：对称的二叉树
 * // 题目：请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和
 * // 它的镜像一样，那么它是对称的。
 */
package datastructure.bst.offer;

import java.util.Arrays;

public class Java28_SymmetricalBinaryTree {
    public static boolean isSym(TreeNode head) {
        return false;
    }

    public static void main(String[] args) {
        TreeNode head = TreeUtil.construct(Arrays.asList(1, 2, 2));
        TreeNode h1 = TreeUtil.construct(Arrays.asList(1, 3, 2));
        System.out.println("value-" + isSym(head) + "; target-true");
        System.out.println("value-" + isSym(h1) + "; target-false");

    }
}
