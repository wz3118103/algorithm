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
    public static boolean aftersort(int[] sequence, int begin, int length) {
        if (sequence == null || length <= 0) {
            return false;
        }

        int root = sequence[begin + length - 1];
        // 在二叉搜索树中左子树的结点小于根节点
        int i = begin;
        for (; i < begin + length - 1; ++i) {
            if (sequence[i] > root) {
                break;
            }
        }

        // 右子树结点必须都大于根节点
        int j = i;
        for (; j < begin + length - 1; ++j) {
            if (sequence[j] < root) {
                return false;
            }
        }

        // 判断左子树是不是二叉搜索树
        boolean left = true;
        if (i > begin) {
            left = aftersort(sequence, begin, i);
        }

        // 判断右子树是不是二叉搜索树
        boolean right = true;
        if (i < begin + length - 1) {
            right = aftersort(sequence, i, length - i - 1);
        }

        return (left && right);
    }

    public static void main(String[] args) {
        int[] ints = {5, 7, 6, 9, 11, 10, 8};
        System.out.println("value-" + aftersort(ints, 0, ints.length) + "; target-true");
        ints = new int[]{7, 4, 6, 5};
        System.out.println("value-" + aftersort(ints, 0, ints.length) + "; target-false");

    }
}
