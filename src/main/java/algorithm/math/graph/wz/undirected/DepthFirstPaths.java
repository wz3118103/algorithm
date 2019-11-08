package algorithm.math.graph.wz.undirected;

import java.util.ArrayDeque;
import java.util.Arrays;

public class DepthFirstPaths {
    private Graph G;
    private final int s;
    private boolean[] marked;
    private int[] edgeTo;

    public DepthFirstPaths(Graph g, int s) {
        G = g;
        this.s = s;
        marked = new boolean[g.V()];
        Arrays.fill(marked, false);
        edgeTo = new int[g.V()];
        Arrays.fill(edgeTo, -1);
        dfs(s);
    }

    private void dfs(int v) {
        checkIndex(v);
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        checkIndex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        // 少了检查和hasPathTo检查
        checkIndex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        ArrayDeque<Integer>  stack = new ArrayDeque<>();
        // 这里计算路径时出了错误，越简洁越不容易处错误
        for (int w = v; w != s; w = edgeTo[w]) {
            stack.push(w);
        }
        stack.push(s);
        return stack;
    }

    private void checkIndex(int v) {
        if (v < 0 || v >= G.V()) {
            throw new IndexOutOfBoundsException();
        }
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

        DepthFirstPaths paths = new DepthFirstPaths(G, 0);
        for (int i = 0; i < G.V(); i++) {
            if (paths.hasPathTo(i)) {
                System.out.printf(0 + "-" + i + ":");
                for (int v : paths.pathTo(i)) {
                    System.out.printf(v + " ");
                }
                System.out.println();
            }
        }
    }
}
