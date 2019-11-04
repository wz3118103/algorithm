package algorithm.sort.wz;

import java.util.Arrays;

public class ShellSort implements Sort{
    private int[] array;

    public ShellSort(int[] array) {
        this.array = array;
    }

    /**
     * 本质是插入排序，只不过是分组插入排序
     * 这个分组体现在程序就是间隔，从大间隔一直到1
     * 间隔也就是分为多少组进行插入排序
     */
    @Override
    public void sort() {
        for (int increment = array.length >>> 1; increment >= 1; increment >>>= 1) {
            // 分为increment组，前0 ~ (increment - 1)分别是increment组的第一个数
            // 因此从increment开始进行插入排序，将元素插入到相应组的相应位置
            for (int i = increment; i < array.length; i++) {
                int e = array[i];
                int j = i - increment;
                while (j >= 0 && e < array[j]) {
                    array[j + increment] = array[j];
                    // 忘了更新j
                    j -= increment;
                }
                array[j + increment] = e;
            }
        }
    }

    @Override
    public void print() {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array = {7, 8, 1, 3, 9, 0, 4, 6, 5, 2};
        Sort sort = new ShellSort(array);
        sort.sort();
        sort.print();
    }
}
