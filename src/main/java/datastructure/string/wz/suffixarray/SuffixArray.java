package datastructure.string.wz.suffixarray;

import java.util.Arrays;

/**
 * 刚开始后缀数组中存的是后缀字符串，但是获取index会有问题
 * 因此改为Suffix数据结构
 */
public class SuffixArray {
    private String text;
    private Suffix[] array;
    private int length;

    public SuffixArray(String text) {
        this.text = text;
        this.length = text.length();
        this.array = new Suffix[length];
        for (int i = 0; i < length; i++) {
            array[i] = new Suffix(text, i);
        }
        Arrays.sort(array);
    }

    public int length() {
        return length;
    }

    /**
     * 返回后缀数组中第i个字符串
     *
     * @param i
     * @return
     */
    public String select(int i) {
        checkIndex(i);
        return array[i].toString();
    }

    /**
     * select(i)的索引
     *
     * @param i
     * @return
     */
    public int index(int i) {
        return array[i].index;
    }

    public int lcp(int i) {
        // 这个i需要特殊处理
        if (i < 1 || i >= length) {
            throw new IndexOutOfBoundsException();
        }

        int min = Math.min(array[i - 1].length(), array[i].length());
        // 注意变量的选取，入参有i，则后续选取可为k等
        for (int k = 0; k < min; k++) {
            if (array[i - 1].charAt(k) != array[i].charAt(k)) {
                return k;
            }
        }

        return min;
    }

    /**
     * 二分查找最后的情况：
     * 1）终止情况low > high
     * 2）当low与high相差不超过1时，mid = low，最终low与high总是相等
     * 2-1）key < array[mid]，high = mid - 1终止
     * 2-2）key > array[mid]，low = mid + 1终止
     * 最终，low总是第一个大于key的索引，high是最后一个小于key的索引
     *
     * @param key
     * @return
     */
    public int rank(String key) {
        int low = 0;
        int high = length;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            int cmp = compare(key, array[mid].toString());
            if (cmp < 0) {
                high = mid - 1;
            } else if (cmp > 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        for (int i = high -2; i < low + 5; i++) {
//            checkIndex(i);
            System.out.println(array[i].toString());
        }

        return low;
    }

    private int compare(String s1, String s2) {
        int min = Math.min(s1.length(), s2.length());
        for (int i = 0; i < min; i++) {
            if (s1.charAt(i) < s2.charAt(i)) {
                return -1;
            } else if (s1.charAt(i) > s2.charAt(i)) {
                return 1;
            }
        }
        return s1.length() - s2.length();
    }


    private void checkIndex(int i) {
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static class Suffix implements Comparable<Suffix> {
        private String text;
        private int index;

        public Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }

        public int length() {
            return text.length() - index;
        }

        public char charAt(int i) {
            chechIndex(i);
            return text.charAt(index + i);
        }

        private void chechIndex(int i) {
            if (i < 0 || i >= length()) {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public int compareTo(Suffix o) {
            int min = Math.min(this.length(), o.length());
            for (int i = 0; i < min; i++) {
                if (this.charAt(i) < o.charAt(i)) {
                    return -1;
                } else if (this.charAt(i) > o.charAt(i)) {
                    return 1;
                }
            }
            return this.length() - o.length();
        }

        @Override
        public String toString() {
            return text.substring(index);
        }
    }

    public static void main(String[] args) {

    }
}
