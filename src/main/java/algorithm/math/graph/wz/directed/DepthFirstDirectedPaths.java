package algorithm.math.graph.wz.directed;

import java.util.ArrayDeque;
import java.util.Arrays;

public class DepthFirstDirectedPaths {
    private Digraph G;
    private int s;
    private boolean[] marked;
    private int[] edgeTo;

    public DepthFirstDirectedPaths(Digraph g, int s) {
        G = g;
        this.s = s;
        marked = new boolean[g.V()];
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

    private void checkIndex(int v) {
        if (v < 0 || v >= G.V()) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean hasPathTo(int v) {
        checkIndex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        checkIndex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int w = v; w != s; w = edgeTo[w]) {
            stack.push(w);
        }
        stack.push(s);
        return stack;
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

        DepthFirstDirectedPaths paths = new DepthFirstDirectedPaths(G, 0);
        for (int v = 1; v < G.V(); v++) {
            if (paths.hasPathTo(v)) {
                System.out.println(paths.pathTo(v));
            }
        }
    }
}
