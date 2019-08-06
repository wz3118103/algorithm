/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题32（三）：之字形打印二叉树
 * // 题目：请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺
 * // 序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，
 * // 其他行以此类推。
 */
package datastructure.bst.offer;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Java32_03_PrintTreesInZigzag  {
    //一个元素代表一行，元素之间不加任何符号。
    public static void print(TreeNode root) {
        if (root == null) {
            return;
        }

        ArrayList<ArrayDeque<TreeNode>> levels = new ArrayList<>(2);
        levels.add(new ArrayDeque<>());
        levels.add(new ArrayDeque<>());

        int current = 0;
        int next = 1;
        levels.get(current).push(root);
        while(!levels.get(0).isEmpty() || !levels.get(1).isEmpty()) {
            TreeNode node = levels.get(current).peek();
            levels.get(current).pop();

            System.out.printf("%d ", node.value);
            if (current == 0) {
                if (node.left != null) {
                    levels.get(next).push(node.left);
                }
                if (node.right != null) {
                    levels.get(next).push(node.right);
                }
            } else {
                if (node.right != null) {
                    levels.get(next).push(node.right);
                }
                if (node.left != null) {
                    levels.get(next).push(node.left);
                }
            }

            if (levels.get(current).isEmpty()) {
                System.out.println();
                current = 1 - current;
                next = 1 - next;
            }

        }

    }

    public static void main(String[] args) {
        TreeNode head = TreeUtil.construct2(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        print(head);
    }
}
