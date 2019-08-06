/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题32（一）：不分行从上往下打印二叉树
 * // 题目：从上往下打印出二叉树的每个结点，同一层的结点按照从左到右的顺序打印。
 */
package datastructure.bst.offer;

import java.util.LinkedList;

public class Java32_01_PrintTreeFromTopToBottom {
    public static void printFromTopToBottom(TreeNode root) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.printf("%d ", node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode node = TreeUtil.construct2(1, 2, 3, 4, 5, 6, 7, 8);
        printFromTopToBottom(node);

    }
}
