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

import java.util.Arrays;

public class Java27_MirrorOfBinaryTree {
    public static TreeNode mirror(TreeNode head) {
        if (head==null){
            return null;
        }
        mirrorCore(head);
        return head;
    }
    private static void mirrorCore(TreeNode head) {
        if (head==null) {
            return;
        }
        if (head.left==null&&head.right==null){
            return;
        }
        TreeNode temp = head.left;
        head.left=head.right;
        head.right=temp;
        if (head.left!=null){
            mirrorCore(head.left);
        }
        if (head.right!=null) {
            mirrorCore(head.right);
        }
    }

    public static void main(String[] args) {
        TreeNode head = TreeUtil.construct(Arrays.asList(1, 2, 3));
        TreeNode h1 = TreeUtil.construct(Arrays.asList(1, 3, 2));
        System.out.println("value-" + TreeUtil.valuesEqual(head, mirror(h1)) + "; target-true");

    }
}
