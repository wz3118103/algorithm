package algorithm.math.graph.wz.undirected;


import java.util.ArrayList;

public class Graph {
    private int V;
    private int E = 0;
    private ArrayList<ArrayList<Integer>> adj;

    public Graph(int v) {
        if (v < 0) {
            throw new IllegalArgumentException();
        }
        V = v;
        adj = new ArrayList<>(v);
        // 不要忘了对每个邻接链表的初始化
        // 这里不能使用set(i, x)，只能使用add
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
        adj.get(w).add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        checkIndex(v);
        return adj.get(v);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= V) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(13);
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
            graph.addEdge(edge[i], edge[i + 1]);
        }
        System.out.println(graph.E());
        for (int i = 0; i < graph.V(); i++) {
            System.out.println(graph.adj(i));
        }
    }

}
