/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题67：把字符串转换成整数
 * // 题目：请你写一个函数StrToInt，实现把字符串转换成整数这个功能。当然，不
 * // 能使用atoi或者其他类似的库函数。
 */
package datastructure.string.offer;

public class Java67_StringToInt {
    static boolean isValid = false;
    public static int strToInt(String str) {
        if(str == null || str.length() <= 0)
            return 0;
        char[] chars = str.toCharArray();
        long num = 0;  //先用long来存储，以防止越界
        boolean minus = false;
        for(int i = 0; i < chars.length; i++){
            if(i == 0 && chars[i] == '-'){
                minus = true;
            }else if(i == 0 && chars[i] == '+'){
                minus = false;
            }else{
                int a = (int)(chars[i] - '0');
                if(a < 0 || a > 9){
                    isValid = false;
                    return 0;
                }
                num = (minus == false) ? (num * 10 + a) : (num * 10 - a);
                isValid = true;  //不放在最后面是为了防止str=‘+’的情况被判断为true
                if((!minus && num > 0x7FFFFFFF)
                        ||(minus && num < 0x80000000)){
                    isValid = false;
                    return 0;
                }
            }
        }
        return (int)num;
    }
}
