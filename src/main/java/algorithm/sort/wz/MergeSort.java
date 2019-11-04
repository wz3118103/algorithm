package algorithm.sort.wz;

import java.util.Arrays;

public class MergeSort implements Sort{
    private int[] array;

    public MergeSort(int[] array) {
        this.array = array;
    }

    /**
     * 分治法
     * 1）将问题递归对半分为两个子问题分别求解
     * 2）当子问题求解完成后，合并子问题的解
     * 3）当子问题足够小时，采用插入排序的方法排序
     */
    @Override
    public void sort() {
        sortCore(0, array.length - 1);
    }

    private void sortCore(int left, int right) {
        int len = right - left + 1;
        if (len < 3) {
            insertionSort(left, right);
            return;
        }
        int mid = left + ((right - left) >> 1);
        sortCore(left, mid);
        sortCore(mid + 1, right);
        int[] tmp = new int[len];
        int i = left, j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            // 确保稳定性，所以需要左边的先放入临时数组，这里必须是<=
            if (array[i] <= array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }

        while (i <= mid) {
            tmp[k++] = array[i++];
        }

        while (j <= right) {
            tmp[k++] = array[j++];
        }

        System.arraycopy(tmp, 0, array, left, len);
    }

    private void insertionSort(int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int e = array[i];
            int j = i - 1;
            // 一定要防御性编程，对于索引类的，一定要确保索引范围正确性
            while (j >= 0 && e < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = e;
        }
    }

    @Override
    public void print() {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array = {7, 8, 1, 3, 9, 0, 4, 6, 5, 2};
        Sort sort = new MergeSort(array);
        sort.sort();
        sort.print();
    }
}
