package datastructure.queueandheap.wz;

/**
 * 编程时特别注意的事情：
 * 1）分析清楚方法的前置和后置条件
 *   前置条件：输入参数需要满足什么要求
 *   后置条件：操作完要处于什么状态
 * 2）注意对对象状态的更新，这里必须要注意更新head tail elements，若有size，也需注意size
 * 3）对于集合类的操作，需要在删除元素时，将相应的slot置为null
 * @param <E>
 */
public class ArrayQueue<E> {
    private Object[] elements;
    private int head;
    private int tail;

    private final static int MIN_CAPACITY = 16;

    public ArrayQueue() {
        elements = new Object[MIN_CAPACITY];
    }

    public void enqueue(E e) {
        if (e == null) {
            throw new NullPointerException("element must not be null");
        }
        elements[tail] = e;
        // 是与操作，搞错了，之前惯性搞成了异或操作
        tail = (tail + 1) & (elements.length - 1);
        if (tail == head) {
            doubleCapacity();
        }
    }

    public E dequeue() {
        // 忘了检测当队列为空时的错误
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty!");
        }
        E value = (E)elements[head];
        // 这里必须将elements[head]置为null，忘了
        elements[head] = null;
        head = (head + 1) & (elements.length - 1);
        return value;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    private void doubleCapacity() {
        int newCapacity = elements.length << 1;
        if (newCapacity < 0) {
            throw new IllegalStateException("Queue is too big");
        }
        int pos = elements.length;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, head, newArray, 0, pos - head);
        System.arraycopy(elements, 0, newArray, pos, tail);
        elements = newArray;

        head = 0;
        tail = pos;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue= new ArrayQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }
        while (!queue.isEmpty()) {
            System.out.printf("%d ", queue.dequeue());
        }
        System.out.println();
        queue.dequeue();

    }
}
