/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题45：把数组排成最小的数
 * // 题目：输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼
 * // 接出的所有数字中最小的一个。例如输入数组{3, 32, 321}，则打印出这3个数
 * // 字能排成的最小数字321323。
 */
package algorithm.math.numbertheory.offer;

import java.util.Arrays;
import java.util.Comparator;

public class Java45_SortArrayForMinNumber {

    public static void printMinNumber(int[] data){
        if(data == null||data.length == 0) {
            return;
        }

        Integer[] array = new Integer[data.length];
        for (int i = 0; i < data.length; ++i) {
            array[i] = data[i];
        }

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String temp1 = o1 + "" + o2;
                String temp2 = o2 + "" + o1;
                return temp1.compareTo(temp2);
            }
        });
//        for(int i = 0;i < data.length - 1;i++){
//            for(int j = 0;j < data.length - 1 - i;j++){
//                if(bigger(data[j],data[j + 1])){
//                    int temp = data[j];
//                    data[j] = data[j + 1];
//                    data[j + 1] = temp;
//                }
//            }
//        }
        for(int item : array){
            System.out.print(item);
            System.out.print(" ");
        }
        System.out.println();
    }
    //if a>=b return true
    public static boolean bigger(int a, int b){
        String temp1 = a + "" + b;
        String temp2 = b + "" + a;
        if(temp1.compareTo(temp2) > 0)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        int[] ints = {3, 32, 321};
        printMinNumber(ints);
        System.out.println("target-321323");
    }
}
