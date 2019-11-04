package algorithm.sort.wz;

import java.util.Arrays;

public class CountingSort implements Sort {
    // 字母表含有的字符个数
    private static final int R = 256;
    private int[] array;

    public CountingSort(int[] array) {
        this.array = array;
    }

    /**
     * step1.统计各字符的频数
     * step2.将频数转换为起始索引：关键是字符r的索引是小于r的字符频数之和
     * step3.遍历数组，排序
     * step4.回写到原数组
     */
    @Override
    public void sort() {
        int[] count = new int[R + 1];
        // step1.统计字符的频数，将字符r的频数放到count[r+1]中
        for (int i = 0; i < array.length; i++) {
            count[array[i] + 1]++;
        }
        // step2.将频数转换为索引
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // step3.排序
        int[] tmp = new int[array.length];
        for (int i = 0; i < array.length; i++){
            tmp[count[array[i]]++] = array[i];
        }

        // step4.回写
        for (int i = 0; i < array.length; i++) {
            array[i] = tmp[i];
        }

    }

    @Override
    public void print() {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array = {7, 8, 1, 3, 9, 1, 0, 4, 6, 5, 2, 3, 8, 9, 1};
        Sort sort = new CountingSort(array);
        sort.sort();
        sort.print();
    }
}
