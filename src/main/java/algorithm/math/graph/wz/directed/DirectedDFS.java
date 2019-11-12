package algorithm.math.graph.wz.directed;

public class DirectedDFS {
    private Digraph G;
    private boolean[] marked;

    public DirectedDFS(Digraph G, int s) {
        this.G = G;
        marked = new boolean[G.V()];
        dfs(s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        this.G = G;
        marked = new boolean[G.V()];
        checkVertices(sources);
        for (int v : sources) {
            if (!marked[v]) {
                dfs(v);
            }
        }
    }

    public boolean marked(int v) {
        checkIndex(v);
        return marked[v];
    }

    private void dfs(int index) {
        checkIndex(index);
        marked[index] = true;
        for (int w : G.adj(index)) {
            if (!marked[w]) {
                dfs(w);
            }
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > G.V()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkVertices(Iterable<Integer> sources) {
        for (int v : sources) {
            checkIndex(v);
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

        DirectedDFS dfs = new DirectedDFS(G, 0);
        System.out.println("connect to 0 vertex: ");
        for (int v = 1; v < G.V(); v++) {
            if (dfs.marked(v)) {
                System.out.printf(v + " ");
            }
        }
        System.out.println();
    }
}
