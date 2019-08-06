package datastructure.list.offer;

public class Java25_MergeSortedLists {
    LinkNode merge(LinkNode head1, LinkNode head2) {
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }

        LinkNode mergedHead = null;
        if (head1.value < head2.value) {
            mergedHead = head1;
            mergedHead.next = merge(head1.next, head2);
        } else {
            mergedHead = head2;
            mergedHead.next = merge(head1, head2.next);
        }
        return mergedHead;
    }
}
