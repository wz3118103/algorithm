package datastructure.stack.wz;

public class PureLinkedStack<E> {
    private int size = 0;
    Node<E> first;

    /**
     * 写的很有问题，需要保留原来的first到f
     * @param e
     */
    public void push(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(e, null, f);
        first = newNode;
        if(f != null) {
            f.prev = newNode;
        }

        size++;
    }

    /**
     * 需要对弹出结点的element和next域置为null
     * @return
     */
    public E pop() {
        if (first == null) {
            throw new IllegalStateException("stack is empty.");
        }

        final E oldValue = first.element;
        final Node<E> f = first;
        final Node<E> next = f.next;
        f.element = null;
        f.next = null;
        first = next;
        if (first != null) {
            first.prev = null;
        }

        size--;
        return oldValue;
    }

    public boolean isEmpty() {
        return size == 0;
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
        PureLinkedStack<Integer> stack = new PureLinkedStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        stack.pop();
    }
}
