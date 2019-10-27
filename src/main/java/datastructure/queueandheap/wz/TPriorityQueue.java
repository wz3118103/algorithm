package datastructure.queueandheap.wz;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 实现最大堆，这里特别注意索引是从1开始的；所以注意中间编程过程就会有一些细节需要注意！
 */
public class TPriorityQueue<E> {
    private Object[] queue;
    private int size;

    private static final int DEFAULT_CAPACITY = 11;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private final Comparator<? super E> comparator;

    public TPriorityQueue() {
        this(DEFAULT_CAPACITY, null);
    }

    public TPriorityQueue(int minCapacity) {
        this(minCapacity, null);
    }

    public TPriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_CAPACITY, comparator);
    }

    public TPriorityQueue(int minCapacity, Comparator<? super E> comparator) {
        // 不要忘了检查入参的有效性
        if (minCapacity < 1) {
            throw new IllegalArgumentException();
        }
        this.queue = new Object[minCapacity];
        this.comparator = comparator;
    }

    public void offer(E e) {
        // 此时1..size已经存储了元素，所以下一个位置为size + 1
        int i = size;
        // 注意这里的判断条件，需要+1，因为有一个位置一直未使用
        if (size + 1 >= queue.length) {
            grow(size + 1);
        }
        // 为空
        if (i == 0) {
            queue[1] = e;
        } else {
            siftUp(i + 1, e);
        }
        size++;
    }

    public E poll() {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }

        E maxValue = (E)queue[1];
        // s指向最后一个元素，并将size减1
        int s = size--;
        E e = (E)queue[s];
        // 注意只有一个元素时，不用处理
        if (s != 1) {
            siftDown(1, e);
        }

        return maxValue;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *  这里当容量过大时，要注意过渡到使用minCapacity来判断
     */
    private void grow(int minCapaciity) {
        int oldCapacity = queue.length;
        // 容量较小时，扩展两倍；容量较大时，扩展1.5倍
        int newCapacity = oldCapacity + (oldCapacity < 64 ? oldCapacity  : (oldCapacity >> 1));
        // 注意判断容量溢出
        if(newCapacity > MAX_ARRAY_SIZE) {
            if (minCapaciity < 0) {
                throw new OutOfMemoryError("queue size is too big");
            }
            newCapacity = minCapaciity > MAX_ARRAY_SIZE ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
        }
        queue = Arrays.copyOf(queue, newCapacity);
    }


    private void siftUp(int index, E e) {
        if (comparator != null) {
            siftUpUsingComparator(index, e);
        } else {
            siftUpComparable(index, e);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int index, E e) {
        Comparable<? super E> key = (Comparable<? super E>)e;
        int child = index;
        int parent = index >> 1;
        while(parent > 0 && key.compareTo((E)queue[parent]) > 0) {
            queue[child] = queue[parent];
            child = parent;
            parent >>= 1;
        }
        // 此时parent已经为0，所以需要child来赋值
        queue[child] = e;
    }

    @SuppressWarnings("unchecked")
    private void siftUpUsingComparator(int index, E e) {
        int child = index;
        int parent = index >> 1;
        while(parent > 0 && comparator.compare(e, (E)queue[parent]) > 0) {
            queue[child] = queue[parent];
            child = parent;
            parent >>= 1;
        }
        // 此时parent已经为0，所以需要child来赋值
        queue[child] = e;
    }

    private void siftDown(int index, E e) {
        if (comparator != null) {
            siftDownUsingComparator(index, e);
        } else {
            siftDownComparable(index, e);
        }
    }

    private void siftDownComparable(int index, E e) {
        Comparable<? super E> key = (Comparable<? super E>)e;
        int parent = index;
        int left = index << 1;
        int right = left + 1;
        int half = size >> 1;
        while (parent <= half) {
            // 简化写法，合并左右对称写法
            int child = left;
            E c = (E) queue[child];
            if (((Comparable<? super E>) queue[right]).compareTo((E) queue[left]) > 0) {
                c = (E) queue[child = right];
            }
            // 此处break，会导致parent没有赋值正确的值
            if (key.compareTo((E) queue[child]) >= 0) {
                break;
            }
            queue[parent] = c;
            parent = child;
            left = parent << 1;
            right = left + 1;
        }
        // 最后一个赋值要放在循环外，之前放在break里面，会导致e无法放到正确的位置！
        queue[parent] = e;
    }

    private void siftDownUsingComparator(int index, E e) {
        int parent = index;
        int left = index << 1;
        int right = left + 1;
        int half = size >> 1;
        while (parent <= half) {
            // 简化写法，合并左右对称写法
            int child = left;
            E c = (E) queue[child];
            if (comparator.compare((E)queue[right], (E)queue[left])  > 0) {
                c = (E) queue[child = right];
            }
            // 此处break，会导致parent没有赋值正确的值
            if (comparator.compare(e, (E) queue[child]) >= 0) {
                break;
            }
            queue[parent] = c;
            parent = child;
            left = parent << 1;
            right = left + 1;
        }
        // 最后一个赋值要放在循环外，之前放在break里面，会导致e无法放到正确的位置！
        queue[parent] = e;
    }



    public static void main(String[] args) {
        TPriorityQueue<Integer> queue = new TPriorityQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.offer(i);
        }
        while (!queue.isEmpty()) {
            System.out.printf("%d ", queue.poll());
        }
        System.out.println();
        System.out.println(queue.poll());
    }



}
