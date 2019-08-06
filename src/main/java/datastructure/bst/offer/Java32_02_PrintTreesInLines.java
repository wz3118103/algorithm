/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题32（二）：分行从上到下打印二叉树
 * // 题目：从上到下按层打印二叉树，同一层的结点按从左到右的顺序打印，每一层
 * // 打印到一行。
 */
package datastructure.bst.offer;

import java.util.LinkedList;

public class Java32_02_PrintTreesInLines {
    public static void print(TreeNode root) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 下一层结点的数目
        int nextLevel = 0;
        // 当前层中还没有打印的数量
        int print = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.printf("%d ", node.value);
            if (node.left != null) {
                queue.offer(node.left);
                ++nextLevel;
            }
            if (node.right != null) {
                queue.offer(node.right);
                ++nextLevel;
            }
            --print;
            if (print == 0) {
                System.out.println();
                print = nextLevel;
                nextLevel = 0;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode head = TreeUtil.construct2(1, 2, 3);
        print(head);
    }
}
