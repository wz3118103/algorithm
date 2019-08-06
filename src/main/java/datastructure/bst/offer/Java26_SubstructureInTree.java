package datastructure.bst.offer;

public class Java26_SubstructureInTree {
    private static class TreeNode {
        double value;
        TreeNode left;
        TreeNode right;
    }

    public static boolean hasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root1 != null && root2 != null) {
            if (equal(root1.value, root2.value)) {
                result = doesTreeHaveTree2(root1, root2);
            }
            if (!result) {
                result = hasSubtree(root1.left, root2);
            }
            if (!result) {
                result = hasSubtree(root1.right, root2);
            }
        }
        return result;
    }

    private static boolean doesTreeHaveTree2(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return  true;
        }
        if (root1 == null) {
            return false;
        }
        if (!equal(root1.value, root2.value)) {
            return false;
        }

        return doesTreeHaveTree2(root1.left, root2.left) &&
                doesTreeHaveTree2(root1.right, root2.right);
    }

    private static boolean equal(double num1, double num2) {
        return Math.abs(num1 - num2) < 0.00000001;
    }
}
