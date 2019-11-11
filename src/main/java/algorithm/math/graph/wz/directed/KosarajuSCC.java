package algorithm.math.graph.wz.directed;

import java.util.ArrayList;

public class KosarajuSCC {
    private Digraph G;
    private int count;
    private int[] id;
    private boolean[] marked;

    public KosarajuSCC(Digraph g) {
        this.G = g;
        this.count = 0;
        this.id = new int[g.V()];
        this.marked = new boolean[g.V()];
        topOrder();
    }

    private void topOrder() {
        // 不要使用拓扑排序（对于有环的无法使用），直接使用逆后序
        DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());
        Iterable<Integer> order = dfs.reversePost();
        for (int v : order) {
            if (!marked[v]) {
                dfs(v);
                count++;
            }
        }
    }

    private void dfs(int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
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

        KosarajuSCC scc = new KosarajuSCC(G);
        System.out.println(scc.count() + " strong components.");
        ArrayList<ArrayList<Integer>> componenets = new ArrayList<>();
        for (int i = 0; i < scc.count(); i++) {
            componenets.add(new ArrayList<>());
        }
        for (int v = 0; v < G.V(); v++) {
            componenets.get(scc.id(v)).add(v);
        }
        for (int i = 0; i < scc.count(); i++) {
            System.out.printf("component " + i + ": ");
            for (int v : componenets.get(i)) {
                System.out.printf(v + " ");
            }
            System.out.println();
        }
    }
}
