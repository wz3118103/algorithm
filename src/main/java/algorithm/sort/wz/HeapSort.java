package algorithm.sort.wz;

import datastructure.queueandheap.wz.PriorityQueue;

import java.util.Arrays;

public class HeapSort implements Sort {
    private int[] array;

    public HeapSort(int[] array) {
        this.array = array;
    }

    /**
     * 1）首先根据数据建立一个二叉堆
     * 2）然后依次将二叉堆的最大值放到数组最后，并逐渐减小堆的大小
     */
    @Override
    public void sort() {
        PriorityQueue pq = new PriorityQueue(array);
        pq.sort();
    }

    @Override
    public void print() {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array = {7, 8, 1, 3, 9, 0, 4, 6, 5, 2};
        Sort sort = new HeapSort(array);
        sort.sort();
//        sort.print();
    }
}
