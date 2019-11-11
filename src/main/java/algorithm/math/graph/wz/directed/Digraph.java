package algorithm.math.graph.wz.directed;

import java.util.ArrayList;

public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private int V;
    private int E;
    private ArrayList<ArrayList<Integer>> adj;

    public Digraph(int v) {
        if (v <= 0) {
            throw new IllegalArgumentException();
        }
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        checkIndex(v);
        checkIndex(w);
        adj.get(v).add(w);
    }

    public Iterable<Integer> adj(int v) {
        // 不要忘了入参检查
        checkIndex(v);
        return adj.get(v);
    }

    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj.get(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            builder.append("vertex " + v + ": ");
            for (int w : adj.get(v)) {
                builder.append(w + "  ");
            }
            builder.append(NEWLINE);
        }
        return builder.toString();
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= V) {
            throw new IndexOutOfBoundsException();
        }
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
        System.out.println(G);
    }
}
