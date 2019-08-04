/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题32（一）：不分行从上往下打印二叉树
 * // 题目：从上往下打印出二叉树的每个结点，同一层的结点按照从左到右的顺序打印。
 */
package datastructure.bst.offer;

public class Java32_01_PrintTreeFromTopToBottom {
    //元素之间没有任何其他符号。比如1,2 打印就是“12”
    public static String print(TreeNode node) {
        return "";
    }

    public static void main(String[] args) {
        TreeNode node = TreeUtil.construct2(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("value-" + print(node) + "; target-12345678");

    }
}
