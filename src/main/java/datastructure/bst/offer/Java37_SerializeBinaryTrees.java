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

public class Java37_SerializeBinaryTrees {
    private static StringBuilder builder = new StringBuilder();

    public static String serialize(TreeNode root) {
        builder.delete(0, builder.length());
        serializeCore(root);
        return builder.toString();
    }

    private static void serializeCore(TreeNode root) {
        if (root == null) {
            builder.append("$,");
        }
        builder.append(root.value).append(",");
        serializeCore(root.left);
        serializeCore(root.right);
    }

    private static int index = 0;
    private static class Num {
        private boolean isNumeric;
        private int num;

        public Num(boolean isNumeric, int num) {
            this.isNumeric = isNumeric;
            this.num = num;
        }
    }

    public static TreeNode deserialize(String string) {
        TreeNode root = null;
        Num result = readString(string);
        if (result.isNumeric) {
            root = new TreeNode(result.num, null, null);
            root.left = deserialize(string);
            root.right = deserialize(string);
        }
        return root;
    }

    private static Num readString(String string) {
        Num result = new Num(false, 0);
        if (index == string.length()) {
            return result;
        }

        int begin = index;
        int len = 0;
        while (index != string.length() && string.charAt(index) != ',') {
            index++;
            len++;
        }

        if (len > 0 && string.charAt(begin) != '$') {
            result.isNumeric = true;
            result.num = Integer.valueOf(string.substring(begin, begin + len));
        }

        return result;
    }

    public static void main(String[] args) {
    }
}
