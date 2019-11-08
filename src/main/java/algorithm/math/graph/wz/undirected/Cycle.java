package algorithm.math.graph.wz.undirected;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 核心是如何判断形成环：
 * 1）首先应该排除自环和平行边
 * 2）其次排除A->B->A， u -> v -> w，其中w等于u的情况，这是对于一条边的两次遍历
 * 3）除了这两种情况，其他的mark[w]标记过的都会形成环
 *    原因：u -> v -> w，其中w标记过，代表uv与w是连通的，因此w肯定是先遍历的，而且是w -> ... -> u -> v
 */
public class Cycle {
    private Graph G;
    private boolean[] marked;
    private int[] edgeTo;
    private ArrayDeque<Integer> cycle;

    public Cycle(Graph g) {
        G = g;
        marked = new boolean[g.V()];
        Arrays.fill(marked, false);
        edgeTo = new int[g.V()];
        Arrays.fill(edgeTo, -1);

        if (hasSelfLoop()) {
            return;
        }
        if (hasParralelEdges()) {
            return;
        }
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(-1, i);
            }
        }
    }

    /**
     * 自环：边的表示为(v,v)，也即adj(v)中含有v
     * @return
     */
    private boolean hasSelfLoop() {
        for (int i = 0; i < G.V(); i++) {
            for (int w : G.adj(i)) {
                if (w == i) {
                    System.out.println("has self loop: " + i + "-" + i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 平行边：也即有两个边(v, w) (w, v)
     * @return
     */
    private boolean hasParralelEdges() {
        for (int i = 0; i < G.V(); i++) {
            // 针对每个顶点都重新计算
            boolean[] marked = new boolean[G.V()];
            for (int w : G.adj(i)) {
                if (marked[w]) {
                    System.out.println("has parallel edges: " + i + "-" + w);
                    return true;
                }
                marked[w] = true;
            }
        }
        return false;
    }

    /**
     * 已经排除了自环和平行边
     * 排除u -> v -> u的情况，其余的对于w已经标记的情况都属于存在环
     * @param u
     * @param v
     */
    private void dfs(int u, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            // 检测到有环，就退出
            if (cycle != null) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(v, w);
            } else if (w != u) {
                cycle = new ArrayDeque<>();
                for (int i = v; i != w; i = edgeTo[i]) {
                    cycle.push(i);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Graph G = new Graph(13);
        int[] edge = {
                0, 5,
                4, 3,
                0, 1,
                9, 12,
                6, 4,
                5, 4,
                0, 2,
                11, 12,
                9, 10,
                0, 6,
                7, 8,
                9, 11,
                5, 3,
                3, 5
        };
        for (int i = 0; i < edge.length; i += 2) {
            G.addEdge(edge[i], edge[i + 1]);
        }

        Cycle finder = new Cycle(G);
        if (finder.hasCycle()) {
            for (int v : finder.cycle()) {
                System.out.printf(v + " ");
            }
            System.out.println();
        } else {
            System.out.println("Graph is acyclic");
        }
    }
}
