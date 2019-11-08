package algorithm.math.graph.wz.mst;

import java.util.ArrayList;

public class EdgeWeightedGraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private ArrayList<ArrayList<Edge>> adj;

    public EdgeWeightedGraph(int v) {
        if (v < 0) {
            throw new IllegalArgumentException();
        }
        V = v;
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public int V() {
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(Edge e) {
        // 不要忘了对入参进行检查
        int u = e.either();
        int v = e.other(u);
        checkIndex(u);
        checkIndex(v);
        adj.get(u).add(e);
        adj.get(v).add(e);
        // 不要忘了更新E
        E++;
    }

    public Iterable<Edge> adj(int v) {
        // 不要忘了对入参进行检查
        checkIndex(v);
        return adj.get(v);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(V + " " + E + NEWLINE);
        for (int i = 0; i < V; i++) {
            builder.append("vertex " + i + ":");
            for (Edge e : adj.get(i)) {
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
        EdgeWeightedGraph G = new EdgeWeightedGraph(8);
        int[] edge = {
                4, 5,
                4, 7,
                5, 7,
                0, 7,
                1, 5,
                0, 4,
                2, 3,
                1, 7,
                0, 2,
                1, 2,
                1, 3,
                2, 7,
                6, 2,
                3, 6,
                6, 0,
                6, 4
        };
        double[] weight = {
                0.35,
                0.37,
                0.28,
                0.16,
                0.32,
                0.38,
                0.17,
                0.19,
                0.26,
                0.36,
                0.29,
                0.34,
                0.40,
                0.52,
                0.58,
                0.93
        };
        for (int i = 0; i < weight.length; i++) {
            Edge e = new Edge(edge[i << 1], edge[(i << 1) + 1], weight[i]);
            G.addEdge(e);
        }

        System.out.println(G);
    }

}
