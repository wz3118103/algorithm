package algorithm.sort.wz;

import java.util.Arrays;

public class BubbleSort implements Sort {
    private int[] array;

    public BubbleSort(int[] array) {
        this.array = array;
    }

    /**
     * 依次将最大的交互到最后；或者依次将最小的交换到最前面
     */
    @Override
    public void sort() {
        for (int max = array.length - 1; max > 0; max--) {
            for (int i = 0; i < max; i++) {
                if (array[i] > array[i + 1]) {
                    swap(i, i+ 1);
                }
            }
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
        Sort sort = new BubbleSort(array);
        sort.sort();
        sort.print();
    }
}
