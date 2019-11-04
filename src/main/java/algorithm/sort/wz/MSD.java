package algorithm.sort.wz;

import java.util.Arrays;

public class MSD implements Sort {
    private String[] array;
    private String[] aux;
    private static final int MIN_LENGTH = 3;
    // 表示的字符为0 - (R - 1)
    private static final int R = 256;

    public MSD(String[] array) {
        this.array = array;
        this.aux = new String[array.length];
    }

    /**
     * 1）从左往右使用countingSort，特别注意，为空的字符也要算进去
     * r字符的索引为所有小于r的字符以及字符串已终止之和
     * count[0] = 0
     * count[1] 存储字符串已经终结的频数（字符串长度为d）
     * count[2] - 存储 r = 0频数，因此整体后移了一位
     * 因此小于r的数目，存储在count[r+1]中
     * 2）对于小数组，采用插入排序
     * 3）计数排序过后，针对d字符相同的分组，进一步对第d+1个字符进行排序
     */
    @Override
    public void sort() {
        sortCore(0, array.length - 1, 0);
    }

    private void sortCore(int low, int high, int d) {
        // 少了索引检测
        System.out.println(low + " " + high);
        checkValid(low, high);
        if (high - low + 1 <= MIN_LENGTH) {
            insertionSort(low, high, d);
            // 少了return
            return;
        }

        int[] count = new int[R + 2];
        // step1.统计频数
        // 相较于LSD而言，后移了1位
        for (int i = low; i <= high; i++) {
            count[charAt(array[i], d) + 2]++;
        }

        // step2.转换为索引
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // step3.排序
        // 因此，相较于LSD，r起始索引存在count[r+1]
        for (int i = low; i <= high; i++) {
            aux[count[charAt(array[i], d) + 1]++] = array[i];
        }

        // step4.回写
        for (int i = low; i <= high; i++) {
            array[i] = aux[i - low];
        }

        print();

        // 针对各个分组排序
        // count[1] ... count[R] 分别存储了 0 ... (R - 1)字符的起始位置
        for (int i = 1;i <= R; i++) {
            // 必须要加上这个判断条件
            if (count[i + 1] > count[i]) {
                sortCore(low + count[i], low + count[i + 1] - 1, d + 1);
            }
        }

    }
    private void checkValid(int low, int high) {
        checkIndex(low);
        checkIndex(high);
        if (low > high) {
            throw new IllegalArgumentException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int charAt(String str, int d) {
        return str.length() <= d ? -1 : str.charAt(d);
    }

    private void insertionSort(int low, int high, int d) {
        for (int i = low + 1; i <= high; i++) {
            String e = array[i];
            int j = i - 1;
            while (j >= low && less(e, array[j], d)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = e;
        }
    }

    private boolean less(String a, String b, int d) {
        for (int i = d; i < Math.min(a.length(), b.length()); i++) {
            // 犯了小错误，这里应该是charAt(i)而不是charAt(d)
            if (a.charAt(i) < b.charAt(i)) {
                return true;
            } else if (a.charAt(i) > b.charAt(i)) {
                // 这个地方刚开始写错了，写成了>=，应该是>，等于则必须向后
                return false;
            }
        }

        return a.length() < b.length();
    }

    @Override
    public void print() {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
//        String[] array = {"abc", "def", "aba", "cba", "jei", "zya", "yhq", "aby"};
        String[] array = {"she", "sells", "seashells", "by", "the",
                "seashore", "the", "shells", "she", "sells", "are",
                "surely", "seashells"};
        MSD msd = new MSD(array);
        msd.sort();
        msd.print();
    }
}
