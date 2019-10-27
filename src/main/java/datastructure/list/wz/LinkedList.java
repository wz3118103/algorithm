package datastructure.list.wz;

public class LinkedList<E> {

    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    public LinkedList() {
    }

    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * 参考标准库更精简的写法
     *
     * @param e
     */
    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(e, l, null);
        last = newNode;
        // 链表为空
        if (l == null) {
            first = newNode;
        } else {
            // 正常
            l.next = newNode;
        }
        size++;
    }

    /**
     * 代码参考标准库更改后，变得非常精简
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        rangeCheckForAdd(index);
        if (index == size) {
            linkLast(e);
        } else {
            linkBefore(e, node(index));
        }
    }

    /**
     * 力求最精简的写法
     * @param e
     * @param node
     */
    private void linkBefore(E e, Node<E> node) {
        Node<E> prev = node.prev;
        Node<E> newNode = new Node<>(e, prev, node);
        node.prev = newNode;
        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }
        // 忘记更新size
        size++;
    }

    public boolean remove(Object e) {
        if (e == null) {
            for (Node<E> iter = first; iter != null; iter = iter.next) {
                if (iter.element == null) {
                    unlink(iter);
                    return true;
                }
            }
        } else {
            for (Node<E> iter = first; iter != null; iter = iter.next) {
                if (iter.element.equals(e)) {
                    unlink(iter);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 参考标准库的写法，将代码精简到最优
     * 这个代码是最需要注意的
     *
     * @param node
     */
    private E unlink(Node<E> node) {
        E oldValue = node.element;
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        // 表头特殊处理
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        // 表尾的特殊处理
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.element = null;
        size--;
        return oldValue;
    }

    /**
     * 最精简的写法
     * @param index
     * @return
     */
    public E remove(int index) {
        rangeCheck(index);
        return unlink(node(index));
    }

    public E get(int index) {
        rangeCheck(index);
        return node(index).element;
    }

    public E set(int index, E e) {
        rangeCheck(index);
        Node<E> update = node(index);
        E oldValue = update.element;
        update.element = e;
        return oldValue;
    }

    public int size() {
        return size;
    }

    private Node<E> node(int index) {
        Node<E> iter = first;
        for (int count = 0; count++ < index; iter = iter.next) {
        }
        return iter;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node<E> iter = first;
        // 这里的终止条件刚开始也错了：iter.next != null
        while (iter != null) {
            s.append(iter.element + " ");
            // 忘了这句迭代
            iter = iter.next;
        }
        return s.toString();
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index " + index + " out of range. size: " + size);
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " out of range. size: " + size);
        }
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
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);

        list.add(1, 11);
        System.out.println(list);

        list.remove(10);
        System.out.println(list);

        list.remove(new Integer(8));
        System.out.println(list);

        list.remove(0);
        System.out.println(list);

        list.remove(new Integer(11));
        System.out.println(list);

        list.remove(3);
        System.out.println(list);

        list.remove(new Integer(3));
        System.out.println(list);

        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        System.out.println(list);

        LinkedList<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list2.add(0, i);
        }
        System.out.println(list2);
        list2.add(list2.size, list2.size);
        System.out.println(list2);

        System.out.println(list2.get(0));
        System.out.println(list2.get(1));
        System.out.println(list2.get(9));
        System.out.println(list2.get(10));

        System.out.println(list2.set(0, 0));
        System.out.println(list2);
    }

}
