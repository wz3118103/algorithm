package datastructure.bst.offer;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: jiang
 * \* Date: 2017/8/5 0005
 * \* Time: 18:04
 * \
 */
public class TreeNode {
    public Integer value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(Integer values, TreeNode left, TreeNode right) {
        this.value = values;
        this.left = left;
        this.right = right;
    }

    public TreeNode(Integer values) {
        this.value = values;
    }
}
