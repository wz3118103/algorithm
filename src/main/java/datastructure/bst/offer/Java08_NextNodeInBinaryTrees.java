/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题8：二叉树的下一个结点
 * // 题目：给定一棵二叉树和其中的一个结点，如何找出中序遍历顺序的下一个结点？
 * // 树中的结点除了有两个分别指向左右子结点的指针以外，还有一个指向父结点的指针。
 */
package datastructure.bst.offer;

public class Java08_NextNodeInBinaryTrees {
    public static TreeNodeParent getnext(TreeNodeParent head, TreeNodeParent node) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println("value-" + getnext(null, null) + "; target-null");
        TreeNodeParent head = new TreeNodeParent(null, 2, null, null);
        head.left = new TreeNodeParent(head, 1, null, null);
        head.right = new TreeNodeParent(head, 2, null, null);
        System.out.println("value-" + getnext(head, head.left) + "; target-" + head);
    }
}
