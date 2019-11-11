package algorithm.math.graph.wz.directed;

public class Topological {
    private Digraph G;
    private Iterable<Integer> topOrder;

    public Topological(Digraph g) {
        G = g;
        DiretedCycle cycle = new DiretedCycle(G);
        // 无环才有拓扑顺序
        if (!cycle.hasCycle()) {
            DepthFirstOrder order = new DepthFirstOrder(G);
            topOrder = order.reversePost();
        }
    }

    public Iterable<Integer> getTopOrder() {
        return topOrder;
    }

    public boolean isDAG() {
        return topOrder != null;
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
        Topological top = new Topological(G);
        if (top.isDAG()) {
            System.out.println(top.getTopOrder());
        } else {
            System.out.println("graph is not a DAG");
        }


        Digraph G2 = new Digraph(13);
        int[] edges2 = {
                2, 3,
                6, 0,
                0, 1,
                2, 0,
                11, 12,
                9, 10,
                9, 11,
                3, 5,
                8, 7,
                5, 4,
                0, 5,
                6, 4,
                6, 9,
                7, 6
        };
        for (int i = 0; i < edges2.length; i += 2) {
            G2.addEdge(edges2[i], edges2[i + 1]);
        }
        Topological top2 = new Topological(G2);
        if (top2.isDAG()) {
            System.out.println(top2.getTopOrder());
        } else {
            System.out.println("graph is not a DAG");
        }
    }
}
