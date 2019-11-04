package algorithm.sort.wz;

import java.util.Arrays;

public class InsertionSort implements Sort{
    private int[] array;

    public InsertionSort(int[] array) {
        this.array = array;
    }

    /**
     * 参考抓扑克牌的思想
     * 将一个数插入到已经排序好的数组中，
     * 从后往前挨个检查（若小于当前数，当前数后移），找到相应的位置插入
     */
    public void sort() {
        for (int i = 1; i < array.length; i++) {
            int e = array[i];
            int j = i - 1;
            // 为了稳定性，必须是<，而不能是<=
            // 这里之前少了j >= 0的检测
            while (j >= 0 && e < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            // 这里之前赋值错写成了array[j] = e
            array[j + 1] = e;
        }
    }

    @Override
    public void print() {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array = {7, 8, 1, 3, 9, 0, 4, 6, 5, 2};
        Sort sort = new InsertionSort(array);
        sort.sort();
        sort.print();
    }
}
