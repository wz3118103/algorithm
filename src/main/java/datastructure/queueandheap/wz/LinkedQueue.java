package datastructure.queueandheap.wz;

public class LinkedQueue<E> {
    private Node<E> head;
    private Node<E> tail;

    public LinkedQueue() {
    }

    public void enqueue(E e) {
        // 放在队尾
        Node<E> newNode = new Node<>(e, tail, null);
        // 队列为空
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
    }

    public E dequeue() {
        // 从对头取出
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty!");
        }
        final Node<E> del = head;
        final Node<E> next = head.next;
        E value = del.element;
        del.next = null;
        del.element = null;
        if (next == null) {
            tail = null;
        } else {
            next.prev = null;
        }
        head = next;

        return value;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> queue= new LinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        while (!queue.isEmpty()) {
            System.out.printf("%d ", queue.dequeue());
        }
        System.out.println();
        queue.dequeue();

    }
}
