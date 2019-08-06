/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题58（一）：翻转单词顺序
 * // 题目：输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 * // 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
 * // 则输出"student. a am I"。
 */
package datastructure.string.offer;

public class Java58_01_ReverseWordsInSentence {
    public static String reverseSentence(String str){
        String result = "";
        if (str == null) {
            return result;
        }
        int start = 0;
        int end = str.length() - 1;
        String temp = reverseWords(str,start,end);


        char[] array = temp.toCharArray();
        for (int i = 0; i < array.length; i++) {
            int low = i;
            while (i < array.length && array[i] != ' ') {
                i++;
            }
            int high = i - 1;
            temp = reverseWords(temp, low, high);
        }
        result = temp;
        return result;
    }

    public static String reverseWords(String str, int start, int end) {

        char[] array = str.toCharArray();
        while (start < end) {
            char temp = array[start];
            array[start] = array[end];
            array[end] = temp;

            start++;
            end--;
        }

        String result = new String(array);
        return result;
    }

}
