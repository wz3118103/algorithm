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

    public static  boolean removeMe(LinkNode head, LinkNode me) {
        return false;
    }

    public static void main(String[] args) {
        LinkNode head = new LinkNode(1, null);
        head.next = new LinkNode(2, null);
        head.next.next = new LinkNode(3, null);
        System.out.println("value-" + removeMe(head, null) + "; target-false");
        System.out.println("value-" + removeMe(head, head.next) + "; target-true");
        System.out.println("value-" + head.next.values.intValue() + "; target-3");
    }
}
