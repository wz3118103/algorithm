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

import java.util.List;

public class Java32_03_PrintTreesInZigzag  {
    //一个元素代表一行，元素之间不加任何符号。
    public static List<String> print(TreeNode head) {
        return null;
    }

    public static void main(String[] args) {
        TreeNode head = TreeUtil.construct2(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        List<String> list = print(head);
        System.out.println("value-" + list.size() + "; target-4");
        System.out.println("value-" + list.get(0) + "; target-1");
        System.out.println("value-" + list.get(1) + "; target-32");
        System.out.println("value-" + list.get(2) + "; target-4567");
        System.out.println("value-" + list.get(3) + "; target-15141312111098");
    }
}
