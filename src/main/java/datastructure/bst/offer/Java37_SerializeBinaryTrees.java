/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题37：序列化二叉树
 * // 题目：请实现两个函数，分别用来序列化和反序列化二叉树。
 */
package datastructure.bst.offer;

import java.util.Arrays;

public class Java37_SerializeBinaryTrees {

    public static String serialize(TreeNode head) {
        return "";
    }

    public static TreeNode deSerize(String string) {
        return null;
    }

    public static void main(String[] args) {
        TreeNode head = TreeUtil.construct(Arrays.asList(1, 2, 3, 4, 5, 6));
        String s = serialize(head);
        TreeNode h2 = deSerize(s);
        System.out.println("value-" + TreeUtil.valuesEqual(head, h2) + "; target-true");
    }
}
