/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题18（一）：在O(1)时间删除链表结点
 * // 题目：给定单向链表的头指针和一个结点指针，定义一个函数在O(1)时间删除该
 * // 结点。
 */
package datastructure.list.offer;

public class Java18_01_DeleteNodeInList {

    public static  void remove(LinkNode head, LinkNode delete) {
        if (head == null || delete == null) {
            return;
        }

        if (delete.next != null) {
            LinkNode next = delete.next;
            delete.values = next.values;
            delete.next = next.next;
        } else if (head == delete) {
            head = delete = null;
        } else {
            LinkNode node = head;
            while (node.next != delete) {
                node = node.next;
            }
            node.next = null;
            delete = null;
        }

    }

    public static void main(String[] args) {
    }
}
