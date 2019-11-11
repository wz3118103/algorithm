package algorithm.math.graph.wz.sp;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 1）无父权重环
 * 2）使用队列进行有环，只有在上一轮中缩小的顶点才加入队列
 */
public class BellmanFordSP {
    private EdgeWeightedDigraph G;
    private int s;
    private boolean[] onQueue;
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private ArrayDeque<Integer> queue;
    private Iterable<DirectedEdge> cycle;
    private int count;

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        this.G = G;
        this.s = s;
        distTo = new double[G.V()];
        // 之前未注意存在正无穷！
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        edgeTo = new DirectedEdge[G.V()];
        queue = new ArrayDeque<>();
        onQueue = new boolean[G.V()];

        sp();
    }

    private void sp() {
        distTo[s] = 0.0;
        onQueue[s] = true;
        queue.offer(s);
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.poll();
            onQueue[v] = false;
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                double dist = distTo[v] + e.getWeight();
                if (distTo[w] > dist) {
                    distTo[w] = dist;
                    edgeTo[w] = e;
                    // 入队放在条件里面而不是外部
                    if (!onQueue[w]) {
                        queue.offer(w);
                        onQueue[w] = true;
                    }
                    count++;
                }
            }
            if (count++ % G.V() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) {
                    return;  // found a negative cycle
                }
            }
        }
    }

    public double distTo(int v) {
        checkIndex(v);
        if (hasNegativeCycle()) {
            throw new IllegalArgumentException("graph has negative cycle");
        }
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        checkIndex(v);
        if (hasNegativeCycle()) {
            throw new IllegalArgumentException("graph has negative cycle");
        }
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        checkIndex(v);
        if (hasNegativeCycle()) {
            throw new IllegalArgumentException("graph has negative cycle");
        }
        ArrayDeque<DirectedEdge> stack = new ArrayDeque<>();
        for (int w = v; w != s; w = edgeTo[w].from()) {
            stack.push(edgeTo[w]);
        }
        return stack;
    }

    private void checkIndex(int v) {
        if (v < 0 || v >= G.V()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(V);
        for (DirectedEdge e : edgeTo) {
            if (e != null) {
                g.addEdge(e);
            }
        }
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(g);
        cycle = finder.cycle();
    }

    private boolean hasNegativeCycle() {
        return cycle != null;
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
                -1.20,
                0.52,
                -1.40,
                -1.25
        };
        for (int i = 0; i < weight.length; i++) {
            DirectedEdge edge = new DirectedEdge(edges[i << 1], edges[(i << 1) + 1], weight[i]);
            G.addEdge(edge);
        }

        BellmanFordSP sp = new BellmanFordSP(G, 0);
        for (int v = 0; v < G.V(); v++) {
            if (sp.hasPathTo(v)) {
                System.out.printf("0-" + v + " min distance: " + sp.distTo(v) + ", ");
                System.out.println("path: " + sp.pathTo(v));
            }
        }
    }
}
