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

    public static  void removeDuplication(LinkNode head) {
        if (head == null) {
            return;
        }
        LinkNode pre = null;
        LinkNode node = head;
        while (node != null) {
            LinkNode next = node.next;
            boolean needDelete = false;
            if (next != null && next.value == node.value) {
                needDelete = true;
            }
            if (!needDelete) {
                pre = node;
                node = node.next;
            } else {
                int value = node.value;
                LinkNode delete = node;
                while (delete != null && delete.value == value) {
                    next = delete.next;
                    delete = next;
                }

                if (pre == null) {
                    head = next;
                } else {
                    pre.next = next;
                }
                node = next;
            }
        }
    }

    public static void main(String[] args) {
        LinkNode linkNode = LinkListUtil.construct(1, 2, 3, 4);
        removeDuplication(linkNode);
        LinkNode linkNode2 = LinkListUtil.construct(1, 2, 2, 3, 4);
        removeDuplication(linkNode2);
    }
}
