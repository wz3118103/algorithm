package datastructure.string.wz.regex;

import algorithm.math.graph.wz.directed.Digraph;
import algorithm.math.graph.wz.directed.DirectedDFS;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class NFA {
    private Digraph G;
    private String regex;
    private int m;

    public NFA(String regex) {
        this.regex = regex;
        this.m = regex.length();
        this.G = new Digraph(m + 1);
        constructNFA();
    }

    /**
     * 目标：构建ε-转换的有向图
     * 1）对于字母表连接，无需处理
     * 2）对于或 (xxx|yyy)，需要添加左括号到第一个y + |到右括号 两条ε-转换
     * 3）对于闭包A*，添加A到*和*到A两条ε-转换
     *    对于闭包(xxx)*，添加左括号到*和*到左括号两条ε-转换
     * 4）对于( ) *，还有与后面字符的正常连接，也需添加一条ε-转换；|已经在或中处理过，不能添加
     */
    private void constructNFA() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            char c = regex.charAt(i);
            int lp = i;
            // (和|需要压入栈，以备后续添加ε-转换
            if (c == '(' || c == '|') {
                stack.push(i);
            } else if (c == ')') {
                int or = stack.pop();
                if (regex.charAt(or) == '|') {
                    // 这里不能另外定义left，还必须是lp，因为或后面也可能是*
                    lp = stack.pop();
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                } else if (regex.charAt(or) == '('){
                    // 此时lp为左括号
                    lp = or;
                }
            }
            // 这里合并了A*和(xxx)*两种情况，通过lp来合并
            if (i < m - 1 && regex.charAt(i + 1) == '*') {
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }
            // 这里也合并了很多情况，包括最后的)，连接到最终的状态m。regex对应状态0 ~ (m-1)
            if (c == '(' || c == ')' || c == '*') {
                G.addEdge(i, i + 1);
            }
        }
    }

    /**
     * step1.G是ε-转换有向图，首先找出从0状态所有可达的状态，作为初始状态集
     * step2.开始遍历文本，针对每个文本字符txt[i]，
     *       遍历上一状态集，找出可匹配状态，并加入下一状态集；
     *       针对可匹配转换找出的状态集，通过ε-转换找出所有状态
     * step3.最后，如果最终状态集中包含终止状态，则表明该正则表达式可识别该文本
     *
     * @param txt
     * @return
     */
    public boolean recognizes(String txt) {
        // step1.初始状态集pc
        ArrayList<Integer> pc = new ArrayList<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) {
                pc.add(v);
            }
        }

        for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i);
            // 异常输入处理
            if (c == '(' || c == ')' || c == '|' || c == '*') {
                throw new IllegalArgumentException("txt containes metacharacter");
            }

            // step2.匹配转换
            ArrayList<Integer> match = new ArrayList<>();
            for (int j : pc) {
                if (j == m) {
                    continue;
                }
                if (regex.charAt(j) == c || regex.charAt(j) == '.') {
                    match.add(j + 1);
                }
            }

            // step3.对匹配转换后的match状态进行ε-转换
            pc = new ArrayList<>();
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < G.V(); v++) {
                if (dfs.marked(v)) {
                    pc.add(v);
                }
            }

            // 一个优化，之前没有考虑到
            if (pc.size() == 0) {
                return false;
            }
        }

        // step4.检查最终的状态集是否包含终止状态
        for (int i : pc) {
            if (i == m) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] strings= {"AC", "AD", "AAA", "ABD", "ADD", "BCD", "ABCCBD",
                "BABAAA", "BABBAAA"
        };
        // 测试GREP，文本中是否包含regex模式的字符串，所以前后会添加上"(.*"和".*)"
        String pattern = "(A*B|AC)D";
        String regex = "(.*" + pattern + ".*)";
        NFA nfa = new NFA(regex);
        for (String s : strings) {
            if (nfa.recognizes(s)) {
                System.out.println(s);
            }
        }
    }
}
