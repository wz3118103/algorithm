/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题58（二）：左旋转字符串
 * // 题目：字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * // 请定义一个函数实现字符串左旋转操作的功能。比如输入字符串"abcdefg"和数
 * // 字2，该函数将返回左旋转2位得到的结果"cdefgab"。
 */
package datastructure.string.offer;

public class Java58_02_LeftRotateString {
    //函数Reverse的功能：翻转一段字符串
    private static void reverse(char[] chars,int start,int end) {
        if(chars == null || chars.length < 0 || start < 0 || end > chars.length || start > end) {
            return;
        }
        while (start < end) {
            //首尾一一交换
            char temp=chars[start];
            chars[start]=chars[end];
            chars[end]=temp;

            start++;
            end--;
        }
    }

    //函数LeftRotateString的功能：并且控制翻转的范围（一共是三次翻转），每次分别调动Reverse函数；
    public static String leftRotateString(String string,int n)  {
        char[] data = string.toCharArray();
        if(data != null)  {
            int totalLength = data.length;
            if(totalLength > 0 && n > 0 && n < totalLength) {
                //字符串前面的n个字符的首尾下标
                int firstStart = 0;
                int firstEnd = n - 1;

                //字符串的后面部分字符的首尾下标
                int secondStart = n;
                int secondEnd = totalLength - 1;

                //翻转字符串的前面n个字符
                reverse(data,0,n - 1);
                //翻转字符串的后面部分字符
                reverse(data,n,totalLength - 1);
                //翻转整个字符串
                reverse(data,0,totalLength - 1);

            }
        }
        //data是一个字符数组，在返回的时候要注意先转换成String类型的
        return new String(data);

    }
}
