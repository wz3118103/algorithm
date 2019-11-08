package algorithm.math.graph.wz.mst;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class LazyPrimMST {
    private static final String NEWLINE = System.getProperty("line.separator");
    private EdgeWeightedGraph G;
    private ArrayList<Edge> mst;
    // 默认是最小堆
    // 最大堆的实现方式：new PriorityQueue<>(Comparator.reverseOrder());
    private PriorityQueue<Edge> pq;
    private boolean[] marked;

    public LazyPrimMST(EdgeWeightedGraph g) {
        G = g;
        mst = new ArrayList<>();
        pq = new PriorityQueue<>();
        marked = new boolean[g.V()];
        lazyPrim();
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * 换一种更标准化的写法
     */
    private void lazyPrim() {
        visit(0);
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int u = e.either();
            int v = e.other(u);
            if (marked[u] && marked[v]) {
                continue;
            }
            // 不要忘了更新mst
            mst.add(e);
            if (!marked[u]) {
                visit(u);
            }
            if (!marked[v]) {
                visit(v);
            }
        }
    }

    private void visit(int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (!marked[w]) {
                pq.offer(e);
            }
        }
    }

    private void lazyPrim1() {
        marked[0] = true;
        for (Edge e : G.adj(0)) {
            pq.offer(e);
        }
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (!isInMST(e)) {
                mst.add(e);
                int u = markedPoint(e);
                int v = e.other(u);
                marked[v] = true;
                for (Edge edge : G.adj(v)) {
                    pq.offer(edge);
                }
            }
        }
    }

    private int markedPoint(Edge e) {
        if (isInMST(e)) {
            throw new IllegalArgumentException();
        }
        int u = e.either();
        int v = e.other(u);
        if (marked[u]) {
            return u;
        } else {
            return v;
        }
    }

    private boolean isInMST(Edge e) {
        int u = e.either();
        int v = e.other(u);
        return marked[u] && marked[v];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Edge e : mst) {
            builder.append(e + NEWLINE);
        }
        return builder.toString();
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

        LazyPrimMST mst = new LazyPrimMST(G);
        System.out.println(mst);
    }
}
