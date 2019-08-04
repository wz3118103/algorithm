/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题5：替换空格
 * // 题目：请实现一个函数，把字符串中的每个空格替换成"%20"。例如输入“We are happy.”，
 * // 则输出“We%20are%20happy.”。
 */
package datastructure.string.offer;

public class Java05_ReplaceSpaces {

    public static String replace(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (String.valueOf(str.charAt(i)).equals(" ")) {
                sb.append("%20");
            } else {
                sb.append(str.charAt(i));
            }
        }
        return String.valueOf(sb);
    }

    public static void main(String[] args) {
        String s1 = "i love you";
        String s2 = "i%20love%20you";
        System.out.println("value-" + replace(s1) + "; target-" + s2);
        s1 = "  hello";
        s2 = "%20%20hello";
        System.out.println("value-" + Java05_ReplaceSpaces.replace(s1) + "; target-" + s2);

    }
}
