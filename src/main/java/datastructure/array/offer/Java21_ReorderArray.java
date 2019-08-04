/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题21：调整数组顺序使奇数位于偶数前面
 * // 题目：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有
 * // 奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 */
package datastructure.array.offer;

public class Java21_ReorderArray {
    public static void reorderArray(int[] ints) {
        int begin = 0;
        int end = ints.length-1;
        while (begin<end) {
            while (begin<end&&(ints[begin]&0x1)!=0) {
                begin++;
            }
            while (begin<end&&(ints[end]&0x1)==0) {
                end--;
            }

            int temp = ints[begin];
            ints[begin] = ints[end];
            ints[end] = temp;
        }
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5, 5};
        System.out.println("value-" + trueArray(ints) + "; target-false");
        reorderArray(ints);
        System.out.println("value-" + trueArray(ints) + "; target-true");
        ints = new int[]{3, 4, 5, 55, 55, 55, 88, 88};
        System.out.println("value-" + trueArray(ints) + "; target-false");
        reorderArray(ints);
        System.out.println("value-" + trueArray(ints) + "; target-true");
    }

    //前半是奇数，后面是偶数。
    private static boolean trueArray(int[] ints) {
        int indexo = 0;
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] % 2 == 0) {
                indexo = i;
                break;
            }
        }
        for (int i = 0; i < indexo; i++) {
            if (ints[i] % 2 == 0) {
                return false;
            }
        }
        for (int i = indexo; i < ints.length; i++) {
            if (ints[i] % 2 == 1) {
                return false;
            }
        }
        return true;
    }
}
