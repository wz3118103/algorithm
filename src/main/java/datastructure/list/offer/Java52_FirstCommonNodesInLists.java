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

import java.util.ArrayDeque;

public class Java52_FirstCommonNodesInLists {
    public LinkNode FindFirstCommonNode(LinkNode pHead1, LinkNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }

        ArrayDeque<LinkNode> stack1 = new ArrayDeque<>();
        ArrayDeque<LinkNode> stack2 = new ArrayDeque<>();

        while (pHead1 != null) {
            stack1.push(pHead1);
            pHead1 = pHead1.next;
        }

        while (pHead2 != null) {
            stack2.push(pHead2);
            pHead2 = pHead2.next;
        }

        LinkNode commonListNode = null;

        while (!stack1.isEmpty() && !stack2.isEmpty() && stack1.peek() == stack2.peek()) {
            stack2.pop();
            commonListNode = stack1.pop();
        }

        return commonListNode;
    }

    public static void main(String[] args) {
    }
}
