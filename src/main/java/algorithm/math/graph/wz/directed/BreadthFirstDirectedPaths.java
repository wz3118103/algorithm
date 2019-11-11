package algorithm.math.graph.wz.directed;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BreadthFirstDirectedPaths {
    private Digraph G;
    private int s;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private Queue<Integer> queue;

    public BreadthFirstDirectedPaths(Digraph g, int s) {
        G = g;
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        Arrays.fill(edgeTo, -1);
        distTo = new int[g.V()];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        queue = new ArrayDeque<>();

        bfs();
    }

    private void bfs() {
        queue.offer(s);
        distTo[s] = 0;
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    queue.offer(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        checkIndex(v);
        return marked[v];
    }

    public int distTo(int v) {
        checkIndex(v);
        return distTo[v];
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

    private void checkIndex(int v) {
        if (v < 0 || v >= G.V()) {
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

        BreadthFirstDirectedPaths paths = new BreadthFirstDirectedPaths(G, 0);
        for (int v = 1; v < G.V(); v++) {
            if (paths.hasPathTo(v)) {
                System.out.printf(0 + "-" + v + ": dist-" + paths.distTo(v) + "; ");
                System.out.println(paths.pathTo(v));
            } else {
                System.out.println(0 + "-" + v + " not connetected.");
            }
        }
    }
}
