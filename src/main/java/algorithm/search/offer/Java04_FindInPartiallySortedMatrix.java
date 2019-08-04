/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题4：二维数组中的查找
 * // 题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按
 * // 照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个
 * // 整数，判断数组中是否含有该整数。
 */
package algorithm.search.offer;

public class Java04_FindInPartiallySortedMatrix {

    public static boolean find(int[][] array, int target) {
        if (array == null) {
            return false;
        }
        int row = 0;
        int column = array[0].length-1;
        while (row < array.length && column >= 0){
            if(array[row][column] == target) {
                return true;
            }
            if(array[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args){
        int[][] ints = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.print(ints[i][j] + "  ");
            }
            System.out.println("");
        }
        int number = 3;
        Java04_FindInPartiallySortedMatrix.find(ints, number);
        System.out.println(Java04_FindInPartiallySortedMatrix.find(ints, number));
        number = 10;
        System.out.println(Java04_FindInPartiallySortedMatrix.find(ints, number));
    }
}
