package algorithm.math.graph.wz.undirected;

/**
 * 可达性：计算图G中与源点s相连通的有哪些顶点以及个数
 */
public class DepthFirstSearch {
    private Graph G;
    private int s;
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph g, int s) {
        G = g;
        this.s = s;
        // 不要对数组进行初始化
        marked = new boolean[g.V()];
        dfs(s);
    }

    private void dfs(int v) {
        checkIndex(v);
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(w);
            }
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= G.V()) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 判断v与s是否相连
     * @param v
     * @return
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * 返回与s相连的顶点个数
     * @return
     */
    public int count() {
        return count;
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

        DepthFirstSearch dfs = new DepthFirstSearch(G, 0);
        for (int i = 0; i < G.V(); i++) {
            System.out.println(dfs.marked(i));
        }
        System.out.println(dfs.count());
    }
}
