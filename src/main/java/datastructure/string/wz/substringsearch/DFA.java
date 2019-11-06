package datastructure.string.wz.substringsearch;

public class DFA implements SubStringSearch {
    private String pattern;
    private static final int R = 256;
    private int[][] dfa;

    public DFA(String pattern) {
        this.pattern = pattern;
        this.dfa = new int[R][pattern.length()];
        calculateDfa();
    }

    /**
     * 构造dfa的核心思想：在pat[j]匹配失败时，按照BruteForce思路，需要回退文本指针
     * 并后移一位，此时的DFA状态是什么？
     *
     * 核心中的核心：dfa[c][j] = dfa[c][X]，也即在text[i] = c与pattern[j]失败时，向右移动的距离
     * 与text[i]与pattern[X]失败时移动的距离相同，X可以通过BF思路获得
     * <p>
     * 此时已知了的文本字符是pat[1] ~ pat[j - 1]，DFA的状态就是从0状态开始经过这j-1字符的状态
     */
    private void calculateDfa() {
        dfa[pattern.charAt(0)][0] = 1;
        int X = 0;
        for (int j = 1; j < pattern.length(); j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pattern.charAt(j)][j] = j + 1;
            // 特别注意X的计算：X的状态是从0开始，经过pattern[1]...pattern[j-1]依次转换
            X = dfa[pattern.charAt(j)][X];
        }
    }

    /**
     * 这里的核心就是利用DFA状态机，k = dfa[text[i]][j]表示的是与下一个text[i+1]比较的是pat[k]
     *
     * @param text
     * @return
     */
    @Override
    public int search(String text) {
        int i = 0, j = 0;
        // 这里的i不是BruteForce中的i，而是文本中的i，可以从0取到text.length()-1
        for (; i < text.length() && j < pattern.length(); i++) {
            // 完全不用区分相等情况和不等情况，因为dfa已经包含了相等情况，j = j + 1
            // 这里不是dfa[text.charAt(i + j)][j]，而是dfa[text.charAt(i)][j]，受了BruteForce干扰
            j = dfa[text.charAt(i)][j];
        }
        if (j == pattern.length()) {
            // 寻找起始点的时候，需要前移
            return i - pattern.length();
        }
        return -1;
    }

    public static void main(String[] args) {
        SubStringSearch sss = new DFA("ABRA");
        System.out.println(sss.search("ABACADABRAC"));
    }
}

