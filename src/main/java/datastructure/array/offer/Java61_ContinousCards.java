/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题61：扑克牌的顺子
 * // 题目：从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * // 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王可以看成任意数字。
 */
package datastructure.array.offer;

public class Java61_ContinousCards {
    //0是王
    public static boolean continous(int[] cards) {
        return false;
    }

    public static void main(String[] args) {
        int[] ints = {2, 3, 4, 5, 6};
        System.out.println("value-" + continous(ints) + "; target-true");
        ints = new int[]{10, 11, 12, 13, 0};
        System.out.println("value-" + continous(ints) + "; target-true");
        ints = new int[]{10, 11, 12, 0, 0};
        System.out.println("value-" + continous(ints) + "; target-true");
        ints = new int[]{10, 11, 2, 0, 0};
        System.out.println("value-" + continous(ints) + "; target-false");

    }
}
