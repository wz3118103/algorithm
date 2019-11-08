package algorithm.math.graph.wz.undirected;

import java.util.ArrayDeque;
import java.util.Arrays;

public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private Graph G;
    private final int s;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BreadthFirstPaths(Graph g, int s) {
        G = g;
        this.s = s;
        marked = new boolean[g.V()];
        Arrays.fill(marked, false);
        edgeTo = new int[g.V()];
        Arrays.fill(edgeTo, -1);
        distTo = new int[g.V()];
        Arrays.fill(distTo, INFINITY);
        bfs(s);
    }

    private void bfs(int s) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        distTo[s] = 0;
        marked[s] = true;
        queue.offer(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    // 严重的错误：这里忘记标记marked[w]！
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    queue.offer(w);
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

        BreadthFirstPaths paths = new BreadthFirstPaths(G, 0);
        for (int i = 0; i < G.V(); i++) {
            if (paths.hasPathTo(i)) {
                System.out.printf(0 + "-" + i + ":");
                System.out.printf("dist:" + paths.distTo(i) + ";");
                for (int v : paths.pathTo(i)) {
                    System.out.printf(v + " ");
                }
                System.out.println();
            }
        }
    }


}
