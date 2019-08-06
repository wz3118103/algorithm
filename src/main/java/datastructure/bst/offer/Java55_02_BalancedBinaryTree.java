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

import java.util.Arrays;

public class Java55_02_BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return hight(root) != -1;
    }
    public int hight(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftHight = hight(root.left);
        if(leftHight == -1){
            return -1;
        }
        int rightHight = hight(root.right);
        if(rightHight == -1){
            return -1;
        }
        if(Math.abs(leftHight - rightHight) > 1){
            return -1;
        }
        return Math.max(leftHight,rightHight) + 1;
    }

    public static void main(String[] args) {
    }
}
