package datastructure.array.wz;

import java.util.Arrays;

public class ArrayList {
    private int[] elementData;
    // 不要写成new int[0]
    private static final int[] EMPTY = {};
    private static final int MIN_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    // 当前数组中实际存储的元素个数
    private int size;

    public ArrayList() {
        elementData = EMPTY;
    }

    /**
     * 需要对传入的容量参数进行分情况处理
     * @param capacity
     */
    public ArrayList(int capacity) {
        if (capacity > 0) {
            elementData = new int[capacity];
        } else if (capacity == 0) {
            elementData = EMPTY;
        } else {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
    }

    public int get(int index) {
        rangeCheck(index);

        return elementData[index];
    }

    public int set(int index, int newValue) {
        rangeCheck(index);
        // 注意返回旧值
        int oldValue = elementData[index];
        elementData[index] = newValue;
        return oldValue;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " out of range, size: " + size);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index " + index + " out of range, size: " + size);
        }
    }


    public void add(int e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
    }

    public void add(int index, int e) {
        // 忘记了范围检查
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        size++;
    }

    public int remove(int index) {
        rangeCheck(index);

        int oldValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        // 这里刚开始的并没有考虑周全
        // 对于引用类型，需要置为null，帮助GC
        // elementData[size--] = null;
        size--;
        return oldValue;
    }

    private void ensureCapacity(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }


    private static int calculateCapacity(int[] elementData, int minCapacity) {
        if (elementData == EMPTY) {
            return Math.max(MIN_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void ensureExplicitCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (minCapacity - oldCapacity > 0) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            // 忘记处理newCapacity比minCapacity小的情况
            // 即使newCapacity溢出，newCapacity - minCapacity也仍大于0
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }
            // 忘记处理溢出的情况
            if (newCapacity - MAX_CAPACITY > 0) {
                if (newCapacity < 0) {
                    throw new OutOfMemoryError();
                }
                newCapacity = newCapacity > MAX_CAPACITY ? Integer.MAX_VALUE : MAX_CAPACITY;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }


    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            ret.append(elementData[i]);
            ret.append(" ");
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        ArrayList array = new ArrayList();
        for (int i = 1; i <= 10; i++) {
            array.add(i);
        }
        System.out.println(array);

        int num = Integer.MAX_VALUE;
        int num2 = num + 2;
        System.out.println("num: " + num);
        System.out.println("num2: " + num2);
        if(num2 - num > 0) {
            System.out.println("num2 > num");
        }

        array.add(0, 0);
        System.out.println(array);

        array.add(4, 44);
        System.out.println(array);

//        array.add(-1, -1);
        array.add(20, 20);
    }
}
