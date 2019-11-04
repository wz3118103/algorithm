package algorithm.search.wz;

public class BinarySearch {
    private int[] array;

    public BinarySearch(int[] array) {
        this.array = array;
    }

    public int search(int value) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >>> 1);
            if (array[mid] < value) {
                low = mid + 1;
            } else if(array[mid] > value) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 4, 7, 9};
        BinarySearch bs = new BinarySearch(array);
        System.out.println(bs.search(0));
        System.out.println(bs.search(1));
        System.out.println(bs.search(9));
    }
}
