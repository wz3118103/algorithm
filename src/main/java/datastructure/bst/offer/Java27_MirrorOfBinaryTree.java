/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题27：二叉树的镜像
 * // 题目：请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 */
package datastructure.bst.offer;

import java.util.ArrayDeque;

public class Java27_MirrorOfBinaryTree {
    public static void mirrorRecursively(TreeNode node) {
        if (node == null || (node.left == null && node.right == null)){
            return;
        }

        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        if (node.left != null) {
            mirrorRecursively(node.left);
        }
        if (node.right != null) {
            mirrorRecursively(node.right);
        }
    }

    public static void mirrorIteratively(TreeNode root) {
        if (root == null) {
            return;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (stack.size() > 0) {
            TreeNode node = stack.peek();
            stack.pop();

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
    }

    public static void main(String[] args) {
    }
}
