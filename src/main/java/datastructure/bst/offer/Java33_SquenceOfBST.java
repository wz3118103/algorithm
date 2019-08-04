/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题33：二叉搜索树的后序遍历序列
 * // 题目：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * // 如果是则返回true，否则返回false。假设输入的数组的任意两个数字都互不相同。
 */
package datastructure.bst.offer;

public class Java33_SquenceOfBST  {
    public static boolean aftersort(int[] ints) {
        return false;
    }

    public static void main(String[] args) {
        int[] ints = {5, 7, 6, 9, 11, 10, 8};
        System.out.println("value-" + aftersort(ints) + "; target-true");
        ints = new int[]{7, 4, 6, 5};
        System.out.println("value-" + aftersort(ints) + "; target-false");

    }
}
