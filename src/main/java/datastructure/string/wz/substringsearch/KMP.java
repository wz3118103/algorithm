package datastructure.string.wz.substringsearch;

import java.util.Arrays;

public class KMP implements SubStringSearch {
    private String pattern;
    private int[] next;

    public KMP(String pattern) {
        this.pattern = pattern;
        next = new int[pattern.length()];
        calculateNext();
    }

    /**
     * next[i]本身表示0-i前缀，其最长可匹配前缀子串的结尾字符下标
     *
     * 使用类似动态规划的思想：如何利用next[0]..next[i-1]计算出next[i]
     * 1）假若next[i-1] == k
     *    并且b[k + 1] == b[i]，则next[i] = k + 1
     * 2）若b[k + 1] != b[i]
     *    那么进一步思考，那么进一步考察b[0,i-1]的次长可匹配前缀子串b[0,h]，
     *    则b[0,h]肯定包含在b[0,k]中，并且满足其既是其前缀也是其后缀
     *    因此，可转换为求b[0,k]的最长可匹配前缀子串，也即h = next[k]
     */
    private void calculateNext() {
        next[0] = -1;
        for (int i = 1; i < pattern.length() - 1; i++) {
            int j = i - 1;
            while (j >= 0) {
                if (pattern.charAt(next[j] + 1) == pattern.charAt(i)) {
                    next[i] = next[j] + 1;
                    break;
                }
                j = next[j];
            }
            if (j == -1) {
                next[i] = -1;
            }
        }
    }


    /**
     * 这里就是利用next[]来计算j会移动多远
     * 假设在text[i]于pattern[j]比对时出错，则可根据next[j-1]计算出可移动多少
     * 也即将pattern[next[j-1]]与pattern[j-1]对齐
     *
     * @param text
     * @return
     */
//    @Override
//    public int search(String text) {
//        int i = 0, j = 0;
//        for (; i < text.length() && j < pattern.length();) {
//            if (text.charAt(i) == pattern.charAt(j)) {
//                i++;
//                j++;
//            } else if (j >= 1){
//                j = next[j - 1] + 1;
//            } else {
//                // 这里不能少了i++
//                i++;
//                j = 0;
//            }
//        }
//        if (j == pattern.length()) {
//            return i - pattern.length();
//        }
//        return -1;
//    }

    @Override
    public int search(String text) {
        System.out.println(Arrays.toString(next));
        int j = 0;
        // 这种写法更优，因为对于j = 0不匹配时，直接++i即可，j保持为0
        for (int i = 0; i < text.length(); ++i) {
            // 因为for循环后面又++i，所以一定要找到text.charAt(i) == pattern.charAt(j)，因此需要使用while循环
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                 j = next[j - 1] + 1;
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                ++j;
            }
            if (j == pattern.length()) {
                 return i - pattern.length() + 1;
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        SubStringSearch sss = new KMP("ABABACD");
        System.out.println(sss.search("ABACADABABACDRAC"));
    }
}
