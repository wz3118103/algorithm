package algorithm.math.graph.wz.sp;

import algorithm.math.graph.wz.directed.Topological;

import java.util.ArrayDeque;
import java.util.Arrays;

public class AcyclicSP {
    private EdgeWeightedDigraph G;
    private int s;
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph g, int s) {
        G = g;
        this.s = s;
        checkIndex(s);

        edgeTo = new DirectedEdge[g.V()];
        distTo = new double[g.V()];
        Arrays.fill(distTo, Double.MAX_VALUE);
        distTo[s] = 0.0;

        sp();
    }

    private void sp() {
        Topological topological = new Topological(G);
        if (!topological.isDAG()) {
            throw new IllegalArgumentException("Digraph is not acyclic.");
        }
        Iterable<Integer> order = topological.getTopOrder();
        for (int v : order) {
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                double dist = distTo[v] + e.getWeight();
                if (distTo[w] > dist) {
                    distTo[w] = dist;
                    edgeTo[w] = e;
                }
            }
        }
    }

    public double distTo(int v) {
        checkIndex(v);
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        checkIndex(v);
        return distTo[v] < Double.MAX_VALUE;
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
                5, 4,
                4, 7,
                5, 7,
                5, 1,
                4, 0,
                0, 2,
                3, 7,
                1, 3,
                7, 2,
                6, 2,
                3, 6,
                6, 0,
                6, 4
        };
        double[] weight = {
                0.35,
                0.37,
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

        AcyclicSP sp = new AcyclicSP(G, 5);
        for (int v = 0; v < G.V(); v++) {
            if (sp.hasPathTo(v)) {
                System.out.printf("5-" + v + " min distance: " + sp.distTo(v) + ", ");
                System.out.println("path: " + sp.pathTo(v));
            }
        }
    }
}
