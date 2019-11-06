package datastructure.string.wz.substringsearch;

import org.omg.CORBA.MARSHAL;

import java.util.Arrays;

public class BoyerMoore implements SubStringSearch {
    private String pattern;
    private static final int R = 256;
    // badCharacter[c] = i，表示坏字符c在pattern中最右位置是i
    private int[] badCharacter;
    // goodSuffix[len] = i，表示好后缀长度为len，其在pattern中出现最右位置的起始位置是i
    private int[] goodSuffix;
    // prefix[len] = true，表示好后缀长度为len，其在pattern中正好匹配长度为len的前缀
    private boolean[] prefix;

    public BoyerMoore(String pattern) {
        this.pattern = pattern;
        badCharacter = new int[R];
        // 忘了对数组进行初始化
        goodSuffix = new int[pattern.length()];
        prefix = new boolean[pattern.length()];
        calculateBadCharacter();
        calculateGoodSuffix();
    }

    /**
     * 更新出每个字符的最右位置
     */
    private void calculateBadCharacter() {
        Arrays.fill(badCharacter, -1);
        for (int i = 0; i < pattern.length(); i++) {
            badCharacter[pattern.charAt(i)] = i;
        }
    }

    /**
     * 依次将pattern前缀与pattern自身求最长公共后缀
     */
    private void calculateGoodSuffix() {
        Arrays.fill(goodSuffix, -1);
        Arrays.fill(prefix, false);

        // 这里计算必须从左到右，因为最终呈现的结果必须是右边的为最终结果
        for (int i = 0; i < pattern.length() - 1; i++) {
            // 计算pattern(0, i) 与 pattern整个公共后缀的最长长度
            int j = i;
            int k = 0;
            while (j >= 0 && pattern.charAt(j) == pattern.charAt(pattern.length() - 1 - i + j)) {
                --j;
                ++k;
                // 这个更新应该放在循环内部而不是外部
                goodSuffix[k] = j + 1;
            }

            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

    @Override
    public int search(String text) {
//        System.out.println(Arrays.toString(goodSuffix));
//        System.out.println(Arrays.toString(prefix));
        if (text == null || text.length() < pattern.length()) {
            throw new IllegalArgumentException();
        }
        for (int i = pattern.length() - 1; i < text.length(); ) {
            int j = pattern.length() - 1;
            int index = i;
            while (j >= 0 && text.charAt(index) == pattern.charAt(j)) {
                j--;
                index--;
            }
            if (j == -1) {
                return index + 1;
            }

            // 坏字符规则：将pattern中最近的坏字符移动到i位置对齐
            int shift = Math.max(j - badCharacter[text.charAt(index)], 1);
            // 好后缀规则：
            // 1）将好后缀对齐
            int len = pattern.length() - 1 - j;
            // 对于好后缀规则，必须len > 0才成立，如果不加这一条会出错
            if (len > 0) {
                if (goodSuffix[len] != -1) {
                    shift = Math.max(shift, j + 1 - goodSuffix[len]);
                } else {
                    // 2）前缀与后缀对齐
                    int shift2 = pattern.length();
                    while (--len > 0) {
                        if (prefix[len]) {
                            shift2 = pattern.length() - len;
                        }
                    }
                    shift = Math.max(shift, shift2);
                }
            }

            i += shift;
        }
        return -1;
    }

    public static void main(String[] args) {
        SubStringSearch sss = new BoyerMoore("cabcab");
        System.out.println(sss.search("abdcdiecabcabkdedaca"));
    }
}
