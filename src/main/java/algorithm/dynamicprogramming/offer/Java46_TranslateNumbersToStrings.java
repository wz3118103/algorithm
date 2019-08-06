/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题46：把数字翻译成字符串
 * // 题目：给定一个数字，我们按照如下规则把它翻译为字符串：0翻译成"a"，1翻
 * // 译成"b"，……，11翻译成"l"，……，25翻译成"z"。一个数字可能有多个翻译。例
 * // 如12258有5种不同的翻译，它们分别是"bccfi"、"bwfi"、"bczi"、"mcfi"和
 * // "mzi"。请编程实现一个函数用来计算一个数字有多少种不同的翻译方法。
 *
 * 思路：
 * 自下而上，动态规划，从最小的问题开始 ：
 * f(r)表示以r为开始（r最小取0）到最右端所组成的数字能够翻译成字符串的种数。对于长度为n的数字，f(n)=0,f(n-1)=1,求f(0)。
 * 递推公式为 f(r-2) = f(r-1)+g(r-2,r-1)*f(r)；
 * 其中，如果r-2，r-1能够翻译成字符，则g(r-2,r-1)=1，否则为0。
 *
 * 作者：ryderchan
 * 链接：https://www.jianshu.com/p/80e1841909b7
 * 来源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */
package algorithm.dynamicprogramming.offer;

public class Java46_TranslateNumbersToStrings {
    public static int getTranslationCount(int number){
        if(number < 0)
            return 0;
        if(number == 1)
            return 1;
        return getTranslationCount(Integer.toString(number));
    }
    //动态规划，从右到左计算。
    //f(r-2) = f(r-1)+ g(r-2, r-1) * f(r);
    //如果r-2，r-1能够翻译成字符，则g(r-2,r-1)=1，否则为0
    private static int getTranslationCount(String number) {
        int f1 = 0,f2 = 1,g = 0;
        int temp;
        for(int i=number.length() - 2;i >= 0;i--){
            if(Integer.parseInt(number.charAt(i)+""+number.charAt(i+1))<26)
                g = 1;
            else
                g = 0;
            temp = f2;
            f2 = f2 + g * f1;
            f1 = temp;
        }
        return f2;
    }

    public static void main(String[] args) {
        System.out.println("value-" + getTranslationCount(12258) + "; target-5");
        System.out.println("value-" + getTranslationCount(0) + "; target-1");
    }
}
