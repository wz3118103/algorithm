package algorithm.math.graph.wz.sp;

import datastructure.queueandheap.wz.IndexMinPriorityQueue;

import java.util.ArrayDeque;
import java.util.Arrays;

public class DijkstraSP {
    private EdgeWeightedDigraph G;
    private int s;
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPriorityQueue<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph g, int s) {
        G = g;
        this.s = s;
        checkInvalid();
        checkIndex(s);
        marked = new boolean[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        distTo = new double[g.V()];
        Arrays.fill(distTo, Double.MAX_VALUE);
        pq = new IndexMinPriorityQueue<>(g.V());
        dijkstra();
    }

    private void dijkstra() {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            marked[v] = true;
            scan(v);
        }
    }

    private void scan(int v) {
        for (DirectedEdge e : G.adj(v))  {
            int w = e.to();
            if (!marked[w]) {
                // 注意这里是distTo[v] + e.getWeight()，不要当成了prim算法
                double dist = distTo[v] + e.getWeight();
                if (distTo[w] > dist) {
                    distTo[w] = dist;
                    edgeTo[w] = e;
                    // 注意更新和新插入
                    if (pq.contains(w)) {
                        pq.change(w, distTo[w]);
                    } else {
                        pq.insert(w, distTo[w]);
                    }
                }

            }
        }
    }

    private void checkInvalid() {
        for (DirectedEdge e : G.edges()) {
            if (e.getWeight() < 0) {
                throw new IllegalArgumentException("graph has negative edge");
            }
        }
    }

    public double distTo(int v) {
        checkIndex(v);
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        checkIndex(v);
        return marked[v];
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        checkIndex(v);
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

        DijkstraSP sp = new DijkstraSP(G, 0);
        for (int v = 0; v < G.V(); v++) {
            if (sp.hasPathTo(v)) {
                System.out.printf("0-" + v + " min distance: " + sp.distTo(v) + ", ");
                System.out.println("path: " + sp.pathTo(v));
            }
        }
    }

}
