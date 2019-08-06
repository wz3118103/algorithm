/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题36：二叉搜索树与双向链表
 * // 题目：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求
 * // 不能创建任何新的结点，只能调整树中结点指针的指向。
 */
package datastructure.bst.offer;

import java.util.Arrays;

public class Java36_ConvertBinarySearchTree  {

    public static TreeNode convert(TreeNode root) {
        TreeNode lastNodeInList = convertCore(root);

        TreeNode listHead = lastNodeInList;
        while (listHead != null && listHead.left != null) {
            listHead = listHead.left;
        }
        return listHead;
    }

    public static TreeNode convertCore(TreeNode node){
        if (node == null) {
            return null;
        }

        TreeNode lastNodeInList = null;

        TreeNode current = node;
        if (current.left != null) {
            lastNodeInList = convertCore(current.left);
        }

        current.left = lastNodeInList;
        if (lastNodeInList != null) {
            lastNodeInList.right = current;
        }
        lastNodeInList = current;
        if (current.right != null) {
            lastNodeInList = convertCore(current.right);
        }

        return lastNodeInList;
    }

    public static void main(String[] args) {
        TreeNode node = TreeUtil.construct(Arrays.asList(2, 1, 3));
        System.out.println("value-" + convert(node) == node.left + "; target-true");
    }
}
