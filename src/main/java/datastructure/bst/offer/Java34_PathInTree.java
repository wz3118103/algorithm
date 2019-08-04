/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题34：二叉树中和为某一值的路径
 * // 题目：输入一棵二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所
 * // 有路径。从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 */
package datastructure.bst.offer;

import java.util.List;

public class Java34_PathInTree {
    /*
    如下的路径有“12”和“13”
    *       1

      2            3
    * */
    public static List<String> path(TreeNode head, int sum) {
        return null;
    }

    public static void main(String[] args) {
        TreeNode head = TreeUtil.construct2(10, 5, 12, 4, 7);
//        TreeUtil.print2(head);
        List<String> list = path(head, 22);
        System.out.println("value-" + list.size() + "; target-2");
        System.out.println("value-" + list.contains("1012") + "; target-true");
        System.out.println("value-" + list.contains("1057") + "; target-true");

    }
}
