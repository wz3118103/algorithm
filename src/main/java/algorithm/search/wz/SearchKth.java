package algorithm.search.wz;

public class SearchKth {
    private int[] array;

    public SearchKth(int[] array) {
        this.array = array;
    }

    public int findKth(int k) {
        rangeCheck(k);
        int pivot = partition(0, array.length - 1);
        while (pivot != k) {
            if (pivot < k) {
                pivot = partition(pivot + 1, array.length - 1);
            } else {
                pivot = partition(0, pivot - 1);
            }
        }
        return array[pivot];
    }

    private void rangeCheck(int k) {
        if (k < 0 || k >= array.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int partition(int left, int right) {
        int v = array[right];
        int low = left;
        // [left,low-1]是小于v，[low,i-1]是大于等于v
        for (int i = left; i <= right - 1; i++) {
            // 这里是小于等于都放在左边
            if (array[i] <= v) {
                // 这里不要错写成i++，for语句中已经有了i++，与while三向版本有区别
                swap(low++, i);
            }
        }
        swap(low, right);
        return low;
    }

    private void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {6, 9, 2, 4, 1, 8, 0};
        SearchKth sk = new SearchKth(array);
//        System.out.println(sk.findKth(0));
//        System.out.println(sk.findKth(1));
//        System.out.println(sk.findKth(5));
        System.out.println(sk.findKth(6));
    }
}
