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

    public static TreeNode kthNode(TreeNode pRoot, int k) {
        if(k < 1 || pRoot == null) {
            return null;
        }
        //左子树节点个数
        int leftCount = getNodeCount(pRoot.left);
        if(leftCount < k ){
            //根节点就是我们要找的节点
            if((leftCount+1) == k) {
                return pRoot;
            } else {
                //开始从右子树找节点
                if (getNodeCount(pRoot.right) < (k - leftCount - 1)) {
                    return null;
                }
                return kthNode(pRoot.right, k - (leftCount + 1));
            }
        } else {//在左子树中找
            return kthNode(pRoot.left, k);
        }
    }
    private static int getNodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        count = getNodeCount(root.left) + getNodeCount(root.right) + 1;
        return count;
    }

    public static void main(String[] args) {
        TreeNode head = TreeUtil.construct(Arrays.asList(2, 1, 3));
        System.out.println("value-" + kthNode(head, 1) == head.left + "; target-true");
        System.out.println("value-" + kthNode(head, 2) == head + "; target-true");
        System.out.println("value-" + kthNode(head, 3) == head.right + "; target-true");
    }
}
