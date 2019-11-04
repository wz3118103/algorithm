package algorithm.sort.wz;

import java.util.Arrays;

public class QuickSort implements Sort{
    private int[] array;

    public QuickSort(int[] array) {
        this.array = array;
    }

    /**
     * 分治法
     * 1）选定pivot
     * 2）根据pivot将数组分为两部分
     * 3）对两部分递归进行处理
     */
    @Override
    public void sort() {
        sortCore(0, array.length - 1);
    }

    private void sortCore(int left, int right) {
        // 注意，递归解法都有终止条件，千万不要缺少！
        if (right - left  <= 0) {
            return;
        }
        int pivot = partition(left, right);
        sortCore(left, pivot -  1);
        sortCore(pivot + 1, right);
    }

    private int partition(int left, int right) {
        int mid = array[right];
        // less指向左边小于mid子数组最后索引的下一个
        // 这里注意less起始地址是left不是0
        int less = left;
        for (int i = left; i <= right - 1; i++) {
            // 算法导论的版本是<=，将小于等于的都放在左边
            if (array[i] <= mid) {
                swap(less++, i);
            }
        }
        // 最后交换right和less即可
        swap(less, right);
        return less;
    }

    private void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    @Override
    public void print() {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array = {7, 8, 1, 3, 9, 0, 4, 6, 5, 2};
        Sort sort = new QuickSort(array);
        sort.sort();
        sort.print();
    }
}
