/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题35：复杂链表的复制
 * // 题目：请实现函数ComplexListNode* Clone(ComplexListNode* pHead)，复
 * // 制一个复杂链表。在复杂链表中，每个结点除了有一个m_pNext指针指向下一个
 * // 结点外，还有一个m_pSibling 指向链表中的任意结点或者nullptr。
 */
package datastructure.list.offer;

public class Java35_CopyComplexList {
    static class ComplexListNode {
        private int value;
        ComplexListNode next;
        ComplexListNode sibling;

        public ComplexListNode(int value, ComplexListNode next, ComplexListNode sibling) {
            this.value = value;
            this.next = next;
            this.sibling = sibling;
        }

        public ComplexListNode() {
        }
    }

    public static ComplexListNode copy(ComplexListNode head) {
        cloneNodes(head);
        connectSiblingNodes(head);
        return reconnectNodes(head);
    }

    private static void cloneNodes(ComplexListNode head) {
        ComplexListNode node = head;
        while (node != null) {
            ComplexListNode cloned = new ComplexListNode(node.value, node.next, null);
            node.next = cloned;
            node = cloned.next;
        }
    }


    private static void connectSiblingNodes(ComplexListNode head) {
        ComplexListNode node = head;
        while (node != null) {
            ComplexListNode cloned = node.next;
            if (node.sibling != null) {
                cloned.sibling = node.sibling.next;
            }
            node = cloned.next;
        }
    }

    private static ComplexListNode reconnectNodes(ComplexListNode head) {
        ComplexListNode node = head;
        ComplexListNode clonedHead = null;
        ComplexListNode clonedNode = null;

        if (node != null) {
            clonedHead = clonedNode = node.next;
            node.next = clonedNode.next;
            node = node.next;
        }

        while (node != null) {
            clonedNode.next = node.next;
            clonedNode = clonedNode.next;

            node.next = clonedNode.next;
            node = node.next;
        }

        return clonedHead;
    }

}
