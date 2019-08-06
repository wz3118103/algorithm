package datastructure.list.offer;

public class Java23_EntryNodeInListLoop {
    public static LinkNode findEntryNode(LinkNode head) {
        if (head == null) {
            return null;
        }
        LinkNode meetingNode = findMeetingNode(head);
        if (meetingNode == null) {
            return null;
        }
        int nodesInLoop = 1;
        LinkNode node = meetingNode;
        while (node.next != meetingNode) {
            node = node.next;
            ++nodesInLoop;
        }

        node = head;
        for (int i = 0; i < nodesInLoop; ++i) {
            node = node.next;
        }
        LinkNode node2 = head;
        while (node != node2) {
            node = node.next;
            node2 = node2.next;
        }

        return node;
    }

    private static LinkNode findMeetingNode(LinkNode head) {
        if (head == null) {
            return null;
        }
        LinkNode slow = head.next;
        if (slow == null) {
            return null;
        }

        LinkNode fast = slow.next;
        while (fast != null && slow != null) {
            if (fast == slow) {
                return fast;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        return null;
    }
}
