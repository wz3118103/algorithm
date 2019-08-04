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
    public static Node copy(Node head) {
        return null;
    }

    public static void main(String[] args) {
        Node hed = new Node(1, null, null);
        hed.left = new Node(2, null, null);
        hed.left.left = new Node(3, null, null);
        hed.other = hed.left.left;
        Node copy = copy(hed);
        System.out.println("value-" + copy.v + "; target-" + hed.v);
        System.out.println("value-" + copy.other.v + "; target-" + hed.other.v);
    }

    static class Node {
        public int v;
        public Node left;
        public Node other;

        public Node(int v, Node left, Node other) {
            this.v = v;
            this.left = left;
            this.other = other;
        }
    }
}
