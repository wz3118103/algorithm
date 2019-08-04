/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题52：两个链表的第一个公共结点
 * // 题目：输入两个链表，找出它们的第一个公共结点。
 */
package datastructure.list.offer;

public class Java52_FirstCommonNodesInLists {
    public static LinkNode parent(LinkNode f1, LinkNode f2) {
        return f1;
    }

    public static void main(String[] args) {
        LinkNode l1 = new LinkNode(1, null);
        l1.next = new LinkNode(2, null);
        l1.next.next = new LinkNode(3, null);
        LinkNode l2 = new LinkNode(4, l1.next);
        System.out.println("value-" + parent(l1, l2) == l1.next + "; target-true");
    }
}
