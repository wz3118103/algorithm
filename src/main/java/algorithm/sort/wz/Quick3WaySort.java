package algorithm.sort.wz;

import java.util.Arrays;

public class Quick3WaySort implements Sort {
    private int[] array;

    public Quick3WaySort(int[] array) {
        this.array = array;
    }

    @Override
    public void sort() {
        sort(0, array.length - 1);
    }

    /**
     * 三向快速排序的一般性步骤：
     * 1）用第一个元素作为区分元素v
     * 2）[left, low-1]为小于v，[low, i - 1]等于v，[i, high]未确定，[high+1, right]大于v
     * @param left
     * @param right
     */
    private void sort1(int left, int right) {
        // 递归算法，一定不要忘了终止条件
        if (left >= right) {
            return;
        }
        int v = array[left];
        int low = left + 1;
        int high = right;
        int i = low;
        while (i <= high) {
            if (array[i] < v) {
                swap(low++, i++);
            } else if (array[i] > v) {
                // 此时high元素交换到i位置，要重新进行检测，所以i的索引不更新
                swap(high--, i);
            } else {
                i++;
            }
        }
        // 最后的三向切分完成的结果为：[left+1,low-1]小于v,[low,high]等于v，[high+1,right]大于v
        // 因此将left于low-1交换
        swap(left, low - 1);
        sort(left, low - 2);
        sort(high + 1, right);
    }

    private void sort(int left, int right) {
        // 递归算法，一定不要忘了终止条件
        if (left >= right) {
            return;
        }
        int v = array[left];
        // 这里的不同是从low从left开始，而不是left + 1
        int low = left;
        int high = right;
        int i = left + 1;
        while (i <= high) {
            if (array[i] < v) {
                swap(low++, i++);
            } else if (array[i] > v) {
                // 此时high元素交换到i位置，要重新进行检测，所以i的索引不更新
                swap(high--, i);
            } else {
                i++;
            }
        }
        // 最后的三向切分完成的结果为：[left,low-1]小于v,[low,high]等于v，[high+1,right]大于v
        // 此时就不需要将left于low-1交换，省略了这一步操作
        sort(left, low - 1);
        sort(high + 1, right);
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
//        int[] array = {7, 8, 1, 3, 9, 0, 4, 6, 5, 2};
        int[] array = {7, 8, 1, 3, 9, 0, 4, 6, 5, 2, 3, 6, 7, 2, 1, 0, 8, 8, 3, 2, 8};
        Sort sort = new Quick3WaySort(array);
        sort.sort();
        sort.print();
    }
}
