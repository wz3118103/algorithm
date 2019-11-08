package algorithm.math.graph.wz.undirected;

import java.util.Arrays;

public class ConnectedComponents {
    private Graph G;
    private boolean[] marked;
    private int[] id;
    private int count = 0;

    public ConnectedComponents(Graph g) {
        G = g;
        marked = new boolean[g.V()];
        Arrays.fill(marked, false);
        id = new int[g.V()];
        Arrays.fill(id, 0);
        // 这个处理时核心，针对每个顶点调用dfs
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                count++;
                dfs(i);
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

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
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
                5, 3
        };
        for (int i = 0; i < edge.length; i += 2) {
            G.addEdge(edge[i], edge[i + 1]);
        }

        ConnectedComponents cc = new ConnectedComponents(G);
        System.out.println(cc.count() + " components");
        for (int i = 0; i < G.V(); i++) {
            System.out.println("vertex " + i + ": " + cc.id(i));
        }
    }
}
