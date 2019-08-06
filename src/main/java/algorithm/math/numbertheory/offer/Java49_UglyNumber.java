/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题49：丑数
 * // 题目：我们把只包含因子2、3和5的数称作丑数（Ugly Number）。求按从小到
 * // 大的顺序的第1500个丑数。例如6、8都是丑数，但14不是，因为它包含因子7。
 * // 习惯上我们把1当做第一个丑数。
 */
package algorithm.math.numbertheory.offer;

public class Java49_UglyNumber {
    public static int uglyNumber(int num) {
        int[] uglyNumber = new int[num];
        uglyNumber[0] = 1;
        int uglyIndex = 0, multiply2 = 0, multiply3 = 0, multiply5 = 0;
        while (uglyIndex + 1< num){
            uglyNumber[++uglyIndex] = min(uglyNumber[multiply2] * 2,uglyNumber[multiply3] * 3,uglyNumber[multiply5] * 5);
            //2*3=6，3*2=6，会有重复值，因此下面需要使用if，而不能用if-else
            if(uglyNumber[multiply2] * 2==uglyNumber[uglyIndex])
                multiply2++;
            if(uglyNumber[multiply3] * 3==uglyNumber[uglyIndex])
                multiply3++;
            if(uglyNumber[multiply5] * 5==uglyNumber[uglyIndex])
                multiply5++;
        }
        return uglyNumber[num-1];
    }

    private static int min(int x,int y,int z){
        int temp = x<y?x:y;
        return temp<z?temp:z;
    }

    public static void main(String[] args) {

        System.out.println("value-" + uglyNumber(1) + "; target-1");
        System.out.println("value-" + uglyNumber(2) + "; target-2");
        System.out.println("value-" + uglyNumber(3) + "; target-3");
        System.out.println("value-" + uglyNumber(4) + "; target-4");
        System.out.println("value-" + uglyNumber(5) + "; target-5");
        System.out.println("value-" + uglyNumber(6) + "; target-6");
        System.out.println("value-" + uglyNumber(7) + "; target-8");
        System.out.println("value-" + uglyNumber(8) + "; target-9");
    }
}
