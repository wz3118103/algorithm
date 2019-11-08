package algorithm.math.graph.wz.undirected;

public class Bipartite {
    private Graph G;
    private boolean[] color;
    private boolean[] marked;
    private boolean isBipartite = true;

    public Bipartite(Graph g) {
        G = g;
        color = new boolean[g.V()];
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V() && isBipartite; i++) {
            if (!marked[i]) {
                color[i] = true;
                dfs(i);
            }
        }
    }

    private void dfs(int v) {
        if (!isBipartite) {
            return;
        }
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(w);
            } else if (color[w] == color[v]) {
                isBipartite = false;
                return;
            }
        }
    }
}
