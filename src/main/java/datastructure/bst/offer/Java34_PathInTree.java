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

import java.util.ArrayList;
import java.util.List;

public class Java34_PathInTree {
    private static int currentSum = 0;

    public static List<String> findPath(TreeNode root, int sum) {
        List<String> paths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        findPathCore(root, sum, path, paths);
        return paths;
    }

    public static void findPathCore(TreeNode root, int sum,
                                    List<Integer> path, List<String> paths) {
        currentSum += root.value;
        path.add(root.value);

        boolean isLeaf = root.left == null && root.right == null;
        if (isLeaf & currentSum == sum) {
            StringBuilder pathStr = new StringBuilder();
            for (int i = 0; i < path.size(); ++i) {
                pathStr.append(path.get(i));
                pathStr.append(" ");
            }
            paths.add(pathStr.toString());
        }

        if (root.left != null) {
            findPathCore(root.left, sum, path, paths);
        }
        if (root.right != null) {
            findPathCore(root.right, sum, path, paths);
        }
        currentSum -= root.value;
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode head = TreeUtil.construct2(10, 5, 12, 4, 7);
//        TreeUtil.print2(head);
        List<String> list = findPath(head, 22);
        System.out.println("value-" + list.size() + "; target-2");
        System.out.println("value-" + list.contains("10 12 ") + "; target-true");
        System.out.println("value-" + list.contains("10 5 7 ") + "; target-true");

    }
}
