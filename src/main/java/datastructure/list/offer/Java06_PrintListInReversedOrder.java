/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题6：从尾到头打印链表
 * // 题目：输入一个链表的头结点，从尾到头反过来打印出每个结点的值。
 */
package datastructure.list.offer;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Java06_PrintListInReversedOrder {
    public static ArrayList<Integer> reverse(Node listNode) {

        if (listNode == null) {
            throw new IllegalArgumentException();
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        while (listNode != null) {
            stack.push(listNode.value);
            listNode = listNode.next;
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }

        return list;
    }

    public static void main(String[] args) {
        Node head = new Node(1, null);
        head.next = new Node(2, null);
        StringBuilder builder = new StringBuilder();
        builder.append(2 + "");
        builder.append(1);
        System.out.println("value-" + builder.toString() + "; target-" );

        reverse(head).forEach(item ->{
            System.out.println(item);
        });
    }

    static class Node {
        public Integer value;
        public Node next;

        public Node(Integer value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
