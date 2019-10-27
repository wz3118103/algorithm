package datastructure.array.wz;

import java.util.Arrays;

public class TArrayList<E> {
    private Object[] elementData;
    // 不要写成new int[0]
    private static final Object[] EMPTY = {};
    private static final int MIN_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    // 当前数组中实际存储的元素个数
    private int size;

    public TArrayList() {
        elementData = EMPTY;
    }

    /**
     * 需要对传入的容量参数进行分情况处理
     * @param capacity
     */
    public TArrayList(int capacity) {
        if (capacity > 0) {
            elementData = new Object[capacity];
        } else if (capacity == 0) {
            elementData = EMPTY;
        } else {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
    }

    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    public E set(int index, E newValue) {
        rangeCheck(index);
        // 注意返回旧值
        E oldValue = elementData(index);
        elementData[index] = newValue;
        return oldValue;
    }

    private E elementData(int index) {
        return (E) elementData[index];
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


    public void add(E e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
    }

    public void add(int index, E e) {
        // 忘记了范围检查
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);

        E oldValue = elementData(index);
        fastRemove(index);
        return oldValue;
    }

    /** doSomeReading(S<? extends Foo> foos) {foos.contains(foo); foos.contains(subFoo);}
     * 如果contains(K  k)函数只能接受一个明确类型的参数。
     * 但是在doSomeReading函数中，编译器无法确定到底是什么类型，它是Foo类型，还是SubFoo类型，还是SubSubFoo类型？
     * 因此这里为了适应S<? extends Foo>，将contains的参数类型设置为Object，同理remove等的参数类型也为Object
     * @param value
     * @return 这里的返回值的确不应该是value，因为该值已经实现知晓
     * 而要返回是否删除成功
     */
    public boolean remove(Object value) {
        int index = indexOf(value);
        if (index < 0 || index >= size) {
            return false;
        }

        fastRemove(index);
        return true;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        // 这里之前忘记考虑需要对numMoved是否大于0进行考虑，例如删除的是最后一个元素的情况
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[size--] = null;
    }

    public int indexOf(Object value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (value.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void ensureCapacity(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }


    private static int calculateCapacity(Object[] elementData, int minCapacity) {
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
        TArrayList<Integer> array = new TArrayList<>();
        for (int i = 1; i <= 10; i++) {
            array.add(i);
        }
        System.out.println(array);


        array.add(0, 0);
        System.out.println(array);

        array.add(4, 44);
        System.out.println(array);

        array.remove(new Integer(44));
        System.out.println(array);

        array.remove(new Integer(10));
        System.out.println(array);

//        array.add(-1, -1);
        array.add(20, 20);
    }
}
