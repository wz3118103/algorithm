package algorithm.sort.wz;

import java.util.Arrays;

public class LSD implements Sort{
    private String[] array;
    private String[] aux;
    private static final int R = 256;

    public LSD(String[] array) {
        this.array = array;
    }


    /**
     * 前置条件：所有String是等宽的
     * 算法：从最低位到高位，调用CountingSort
     */
    @Override
    public void sort() {
        checkLen();
        int len = array[0].length();
        // 作为类成员变量，可以避免每次调用countingSort都创建一次
        aux = new String[array.length];
        for (int i = len - 1; i >= 0; i--) {
            countingSort(i);
        }
    }

    private void countingSort(int index) {
        checkIndex(index);
        int[] count = new int[R + 1];
        // step1.统计频数
        for (int i = 0; i < array.length; i++) {
            count[array[i].charAt(index) + 1]++;
        }
        // step2.转换为频数
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // step3.排序
        for (int i = 0; i < array.length; i++) {
            aux[count[array[i].charAt(index)]++] = array[i];
        }

        // step4.回写
        for (int i = 0; i < array.length; i++) {
            array[i] = aux[i];
        }

        print();
    }

    private void checkIndex(int index) {
        int len = array[0].length();
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkLen() {
        int len = array[0].length();
        for (int i = 1; i < array.length; i++) {
            if (array[i].length() != len) {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void print() {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        String[] array = {"abc", "def", "aba", "cba", "jei", "zya", "yhq", "aby"};
        LSD lsd = new LSD(array);
        lsd.sort();
        lsd.print();
    }
}
