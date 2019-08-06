/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题43：从1到n整数中1出现的次数
 * // 题目：输入一个整数n，求从1到n这n个整数的十进制表示中1出现的次数。例如
 * // 输入12，从1到12这些整数中包含1 的数字有1，10，11和12，1一共出现了5次。
 *
 * 思路：
 *   参考：https://blog.csdn.net/u013132035/article/details/80768636
 */
package algorithm.math.others.offer;

public class Java43_NumberOf1  {
    public static int numberOf1Between1AndN(int n, int x){
        if(n < 0 || x < 1 || x > 9){
            return 0;
        }
        int curr, low, temp, high = n, i = 1, total = 0;
        while(high!=0){
            high = n / (int)Math.pow(10, i); //获取第i位的高位
            temp = n % (int)Math.pow(10, i); //
            curr = temp / (int)Math.pow(10, i-1); //获取第i位
            low = temp%(int)Math.pow(10, i-1); //获取第i位的低位
            if(curr == x){ //等于x
                total += high*(int)Math.pow(10, i-1)+ low + 1;
            }else if(curr < x){ //小于x
                total += high*(int) Math.pow(10, i-1);
            }else{ //大于x
                total += (high + 1) * (int)Math.pow(10, i-1);
            }
            i++;
        }
        return total;
    }


    public static void main(String[] args) {
        System.out.println("value-" + numberOf1Between1AndN(12, 1) + "; target-5");
        System.out.println("value-" + numberOf1Between1AndN(10, 1) + "; target-2");

    }
}
