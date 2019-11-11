package algorithm.math.graph.wz.directed;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 有向图的环检测与无向图还是有很大区别，这里必须是栈上
 * 因为边是单向边
 */
public class DiretedCycle {
    private Digraph G;
    ArrayDeque<Integer> cycle;
    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;

    public DiretedCycle(Digraph g) {
        G = g;
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];
        Arrays.fill(edgeTo, -1);
        // 特别注意需要对每一个顶点进行检查
        for (int v = 0; v < g.V(); v++) {
            // 注意不要忘了检查条件
            if (!marked[v] && !hasCycle()) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : G.adj(v)) {
            if (hasCycle()) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(w);
            } else if (onStack[w]) {
                cycle = new ArrayDeque<>();
                cycle.push(w);
                for (int u = v; u != w; u = edgeTo[u]) {
                    cycle.push(u);
                }
                cycle.push(w);
            }
        }
        // 特别注意，不要忘了这个，递归代码必须遵守的规则
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(13);
        int[] edges = {
                4, 2,
                2, 3,
                3, 2,
                6, 0,
                0, 1,
                2, 0,
                11, 12,
                12, 9,
                9, 10,
                9, 11,
                8, 9,
                10, 12,
                11, 4,
                4, 3,
                3, 5,
                7, 8,
                8, 7,
                5, 4,
                0, 5,
                6, 4,
                6, 9,
                7, 6
        };
        for (int i = 0; i < edges.length; i += 2) {
            G.addEdge(edges[i], edges[i + 1]);
        }

        DiretedCycle cycle = new DiretedCycle(G);
        if (cycle.hasCycle()) {
            System.out.println(cycle.cycle());
        }
    }
}
