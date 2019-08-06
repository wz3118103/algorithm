package datastructure.list.offer;

public class Java24_ReverseList {
    public static LinkNode reverseList(LinkNode head) {
        LinkNode reverseHead = null;
        LinkNode node = head;
        LinkNode prev = null;
        while (node != null) {
            LinkNode next = node.next;
            if (next == null) {
                reverseHead = node;
            }
            node.next = prev;
            prev = node;
            node = next;
        }
        return reverseHead;
    }
}
