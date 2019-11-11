package algorithm.math.graph.wz.sp;

import java.util.ArrayList;

public class EdgeWeightedDigraph {
    public static final String NEWLINE = System.getProperty("line.separator");
    private int V;
    private int E;
    private ArrayList<ArrayList<DirectedEdge>> adj;

    public EdgeWeightedDigraph(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(DirectedEdge e) {
        checkIndex(e.from());
        checkIndex(e.to());
        adj.get(e.from()).add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        checkIndex(v);
        return adj.get(v);
    }

    public Iterable<DirectedEdge> edges() {
        ArrayList<DirectedEdge> edges = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            edges.addAll(adj.get(v));
        }
        return edges;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int v = 0; v < V; v++) {
            builder.append("vertex " + v + ": ");
            for (DirectedEdge e : adj.get(v)) {
                builder.append(e + "  ");
            }
            builder.append(NEWLINE);
        }
        return builder.toString();
    }

    private void checkIndex(int v) {
        if (v < 0 || v >= V) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
        int[] edges = {
                4, 5,
                5, 4,
                4, 7,
                5, 7,
                7, 5,
                5, 1,
                0, 4,
                0, 2,
                7, 3,
                1, 3,
                2, 7,
                6, 2,
                3, 6,
                6, 0,
                6, 4
        };
        double[] weight = {
                0.35,
                0.35,
                0.37,
                0.28,
                0.28,
                0.32,
                0.38,
                0.26,
                0.39,
                0.29,
                0.34,
                0.40,
                0.52,
                0.58,
                0.93
        };
        for (int i = 0; i < weight.length; i++) {
            DirectedEdge edge = new DirectedEdge(edges[i << 1], edges[(i << 1) + 1], weight[i]);
            G.addEdge(edge);
        }
        System.out.println(G);
    }
}
