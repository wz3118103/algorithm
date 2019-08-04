/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题18（二）：删除链表中重复的结点
 * // 题目：在一个排序的链表中，如何删除重复的结点？例如，在图3.4（a）中重复
 * // 结点被删除之后，链表如图3.4（b）所示。
 */
package datastructure.list.offer;

public class Java18_02_DeleteDuplicatedNode {

    public static  LinkNode removeDeplicated(LinkNode head) {
        return head;
    }

    public static void main(String[] args) {
        LinkNode linkNode = LinkListUtil.construct(1, 2, 3, 4);
        System.out.println("value-" + LinkListUtil.equels(linkNode, removeDeplicated(linkNode)) + "; target-true");
        LinkNode linkNode2 = LinkListUtil.construct(1, 2, 2, 3, 4);
        System.out.println("value-" + LinkListUtil.equels(removeDeplicated(linkNode2), linkNode) + "; target-true");
    }
}
