package datastructure.stack.wz;

public class ArrayStack<E> {
    private Object[] elements;
    private int head;
    private int tail;
    private static final int MIN_CAPACITY = 16;

    /**
     * 默认分配16大小的空间，保证是2的次幂，保证位操作可以使用
     */
    public ArrayStack() {
        elements = new Object[MIN_CAPACITY];
    }

    public void push(E e) {
        // 不要压入null
        if (e == null) {
            throw new NullPointerException();
        }

        // 这里错写成head--导致不更新，忘记了+elements.length，导致负数报错
//        head = (--head + elements.length) % elements.length;
//        elements[head] = e;

        // 最精简最高效的写法，使用位操作
        elements[head = (head - 1) & (elements.length - 1)] = e;

        if (head == tail) {
            doubleCapacity();
        }
    }

    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("stack is empty");
        }
        E result = (E)elements[head];
        elements[head] = null;
        // 这里错写成了head++，导致head一直不更新
//        head = (++head) % elements.length;
        head = (head + 1) & (elements.length - 1);

        return result;
    }

    /**
     * head == tail正常情况下表示空
     * 在push的时候遇到这种情况，表示栈满了，此时扩容两倍，可保证在任何正常情况
     * head == tail都表示空
     * @return
     */
    public boolean isEmpty() {
        return head == tail;
    }

    private void doubleCapacity() {
        assert head == tail;
        int newCapacity = elements.length << 1;
        // 溢出
        if (newCapacity < 0) {
            throw new IllegalStateException("stack is too big");
        }

        int rightNum = elements.length - head;

        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, head, newArray, 0, rightNum);
        System.arraycopy(elements, 0, newArray, rightNum, tail);
        elements = newArray;
        // 这里忘了更新head tail，导致出现错误
        head = 0;
        tail = rightNum;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        stack.pop();
    }
}
