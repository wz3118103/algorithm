/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题68：树中两个结点的最低公共祖先
 * // 题目：输入两个树结点，求它们的最低公共祖先。
 */
package datastructure.bst.offer;

public class Java68_CommonParentInTree {

    public static TreeNode getLowestCommonParentBST(TreeNode root,TreeNode node1,TreeNode node2) {
        while(true) {
            if(root == null)
                return root;
            if(root.value < node1.value && root.value < node2.value)
                root = root.right;
            else if(root.value > node1.value && root.value > node2.value)
                root = root.left;
            else
                return root;
        }
    }


    public static TreeNode getLowestCommonParent(TreeNode root,TreeNode node1,TreeNode node2) {
        if (root == null || root == node1 || root == node2)
            return root;
        TreeNode left = getLowestCommonParent(root.left, node1, node2);
        TreeNode right = getLowestCommonParent(root.right, node1, node2);
        return left == null ? right : (right == null ? left : root);
    }
}
