package datastructure.queueandheap.wz;

import java.util.Arrays;

/**
 * 在堆数组中存储索引i，同时通过数组keys将索引i与key关联起来，保证堆性质的比较是通过key来实现的
 * @param <K>
 */
public class IndexMinPriorityQueue<K extends Comparable<K>> {
    private int capacity;
    private int size;
    // 实际的堆数组，所有的堆操作都是在pq上进行，但是这里存储的是索引，比较的时候需要通过索引关联的key来进行比较
    private int[] pq;
    // 索引关联的key
    private K[] keys;
    // 索引在堆数组中的位置，这个与pq是相反的，引入这个数组的核心目的是能够通过索引快速找到存储在堆数组中的位置
    private int[] qp;

    public IndexMinPriorityQueue(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        keys = (K[]) (new Comparable[capacity + 1]);
        pq = new int[capacity + 1];
        qp = new int[capacity + 1];
        Arrays.fill(qp, -1);
        Arrays.fill(pq, -1);
    }

    /**
     * @param i
     * @param key
     */
    public void insert(int i, K key) {
        checkIndex(i);
        if (key == null) {
            throw new NullPointerException();
        }
        if (contains(i)) {
            throw new IllegalArgumentException("duplicate index " + i);
        }
        size++;
        // pq堆数组，索引是从1开始的
        pq[size] = i;
        qp[i] = size;
        keys[i] = key;
        siftUp(size);
    }

    public void change(int i, K key) {
        checkIndex(i);
        if (key == null) {
            throw new NullPointerException();
        }
        if (key.compareTo(keys[i]) == 0) {
            return;
        }
        K old = keys[i];
        keys[i] = key;
        if (old.compareTo(key) < 0) {
            siftDown(qp[i]);
        } else {
            siftUp(qp[i]);
        }
    }

    public boolean contains(int i) {
        checkIndex(i);
        return qp[i] != -1;
    }

    /**
     * 将i = qp[index]处替换为size处
     * @param index
     */
    public void delete(int index) {
        checkIndex(index);
        K key = keys[pq[size]];
        int i = qp[index];
        pq[i] = pq[size];
        qp[pq[size]] = i;
        pq[size] = -1;
        --size;
        keys[index] = null;
        qp[index] = -1;
        siftDown(i);

    }

    public K min() {
        return keys[pq[1]];
    }

    public int minIndex() {
        return pq[1];
    }

    public int delMin() {
        int index = pq[1];
        pq[1] = pq[size];
        qp[pq[1]] = 1;
        // 将size交换到1处，需要删除原来1对应的pq[1]处的值
        keys[index] = null;
        qp[index] = -1;
        pq[size] = -1;
        --size;
        siftDown(1);
        return index;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 将堆中的下标命名为i
    // 将索引命名为index
    private void siftDown(int i) {
        // 同理这里记住也是pq[i]
        int sift = pq[i];
        int left = i << 1;
        int right = left + 1;
        int child = left;
        // 这里忘了right <= size
        if (right <= size && less(pq[right], pq[left])) {
            child = right;
        }
        // 这里是size而不是capacity
        // 这里是child <= size而不是right <= size
        while (child <= size && less(pq[child], sift)) {
            pq[i] = pq[child];
            qp[pq[child]] = i;
            i = child;
            child = left = i << 1;
            right = left + 1;
            // 这里忘了right <= size
            if (right <= size && less(pq[right], pq[left])) {
                child = right;
            }
        }
        pq[i] = sift;
        qp[sift] = i;
    }


    // 将堆中的下标命名为i
    // 将索引命名为index
    private void siftUp(int i) {
        // 这里记住的pq[i]而不是i
        int sift = pq[i];
        int parent = i >>> 1;
        // 因为这里不是交换，所以不是less(i, parent)，而是固定拿sift比较，所以要修改less
        while (parent >= 1 && less(sift, pq[parent])) {
            pq[i] = pq[parent];
            qp[pq[parent]] = i;
            i = parent;
            parent >>>= 1;
        }
        pq[i] = sift;
        qp[sift] = i;
    }


    private boolean less(int i, int j) {
        return keys[i].compareTo(keys[j]) < 0;
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= capacity) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMinPriorityQueue<String> pq = new IndexMinPriorityQueue<>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
//        while (!pq.isEmpty()) {
//            int i = pq.delMin();
//            System.out.println(i + " " + strings[i]);
//        }
//        System.out.println();

//        pq.delete(0);
//        System.out.println();

        pq.change(0, "a");
        System.out.println();
        pq.change(0, "zoom");
        System.out.println();
        pq.change(0, "it");
        System.out.println();
    }
}
