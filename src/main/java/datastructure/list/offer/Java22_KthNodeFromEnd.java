package datastructure.list.offer;

public class Java22_KthNodeFromEnd {
    public static LinkNode findKthToTail(LinkNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }

        LinkNode before = head;
        LinkNode after = null;

        for (int i = 0; i < k - 1; ++i) {
            if (before.next != null) {
                before = before.next;
            } else {
                return null;
            }
        }

        after = head;
        while (before.next != null) {
            before = before.next;
            after = after.next;
        }
        return before;
    }
}
