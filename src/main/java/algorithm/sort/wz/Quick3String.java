package algorithm.sort.wz;

import java.util.Arrays;

public class Quick3String implements Sort{
    private String[] array;
    private static final int MIN_LENGTH = 3;

    public Quick3String(String[] array) {
        this.array = array;
    }

    @Override
    public void sort() {
        sort(0, array.length - 1, 0);
    }

    private void sort2(int left, int right, int d) {
        if (left >= right) {
            return;
        }

        System.out.println(left + " " + right + " " + d);
        int low = left;
        int high = right;
        int i = left + 1;
        String v = array[left];
        // 三向快排：[left, low-1]小于v，[low,high]等于v，[high+1,right]大于v
        while (i <= high) {
            if (less2(array[i], v, d)) {
                swap(low++, i++);
            } else if (less2(v, array[i], d)) {
                swap(high--, i);
            } else {
                i++;
            }
        }
        sort2(left, low - 1, d);
        // 这里犯了严重错误，只有长度允许，才继续
        if (v.length() > d + 1) {
            sort2(low, high, d + 1);
        }
        sort2(high + 1, right, d);
    }

    private boolean less2(String a, String b, int d) {
        if (a.length() > d && b.length() > d) {
            return a.charAt(d) < b.charAt(d);
        }
        return a.length() < b.length();
    }

    private void sort(int left, int right, int d) {
        if (right - left + 1 <= MIN_LENGTH) {
            insertionSort(left, right, d);
            return;
        }

        int v = charAt(array[left], d);
        int low = left;
        int high = right;
        int i = left + 1;
        while (i <= high) {
            if (charAt(array[i], d) < v) {
                swap(low++, i++);
            } else if(charAt(array[i], d) > v) {
                swap(high--, i);
            } else {
                i++;
            }
        }
        sort(left, low - 1, d);
        // 只要v > 0，就可以d+1，因为d可以去到a.length()，超出一位没有问题
        if (v > 0) {
            sort(low, high, d+1);
        }
        sort(high + 1, right, d);
    }

    // 注意，d可以为a.length()
    private int charAt(String a, int d) {
        assert d >= 0 && d <= a.length();
        if (d == a.length()) {
            return -1;
        }
        return a.charAt(d);
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

    private void swap(int i, int j) {
        String tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    @Override
    public void print() {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        String[] array = {"she", "sells", "seashells", "by", "the",
                "seashore", "the", "shells", "she", "sells", "are",
                "surely", "seashells"};
        Quick3String sort = new Quick3String(array);
        sort.sort();
        sort.print();
    }
}
