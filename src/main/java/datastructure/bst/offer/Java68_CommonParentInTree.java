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

    public static TreeNode parent(TreeNode node1, TreeNode node2) {
        return null;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1, null, null);
        head.left = new TreeNode(2, null, null);
        TreeNode left = new TreeNode(3, null, null);
        head.left.left = left;
        TreeNode right = new TreeNode(4, null, null);
        head.left.right = right;
//        TreeUtil.print(head);
        System.out.println("value-" + parent(right, left) == head.left + "; target-true");

    }
}
