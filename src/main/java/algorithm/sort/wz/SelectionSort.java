package algorithm.sort.wz;

import java.util.Arrays;

public class SelectionSort implements Sort{
    private int[] array;

    public SelectionSort(int[] array) {
        this.array = array;
    }

    /**
     * 依次选择最大的放到最后；或者最小放到最前面
     */
    @Override
    public void sort() {
        for (int right = array.length - 1; right > 0; right--) {
            int max = 0;
            // i可以从1开始而不是0
            for (int i = 1; i <= right; i++) {
                if (array[i] > array[max]) {
                    max = i;
                }
            }
            swap(max, right);
        }
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
        Sort sort = new SelectionSort(array);
        sort.sort();
        sort.print();
    }
}
