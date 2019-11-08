package algorithm.math.graph.wz.mst;

import datastructure.disjointsets.wz.UnionFind;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 总是找权重最小的边加入mst，且与当前部分mst不形成环
 */
public class KruskalMST {
    private static final String NEWLINE = System.getProperty("line.separator");
    private EdgeWeightedGraph G;
    private ArrayList<Edge> mst;
    private PriorityQueue<Edge> pq;

    public KruskalMST(EdgeWeightedGraph g) {
        G = g;
        mst = new ArrayList<>();
        pq = new PriorityQueue<>();
        for (Edge e : g.edges()) {
            pq.offer(e);
        }
        kruskal();
    }

    /**
     * 对于判断是否构成环，可使用UF算法
     */
    private void kruskal() {
        // 添加mst.size() < G.V() - 1可加快程序
        UnionFind uf = new UnionFind(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            // 这种方式判断环是否存在有问题
            if (uf.find(v) == uf.find(w)) {
                continue;
            }
            mst.add(e);
            uf.union(v, w);
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Edge e : mst) {
            builder.append(e + NEWLINE);
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(8);
        int[] edge = {
                4, 5,
                4, 7,
                5, 7,
                0, 7,
                1, 5,
                0, 4,
                2, 3,
                1, 7,
                0, 2,
                1, 2,
                1, 3,
                2, 7,
                6, 2,
                3, 6,
                6, 0,
                6, 4
        };
        double[] weight = {
                0.35,
                0.37,
                0.28,
                0.16,
                0.32,
                0.38,
                0.17,
                0.19,
                0.26,
                0.36,
                0.29,
                0.34,
                0.40,
                0.52,
                0.58,
                0.93
        };
        for (int i = 0; i < weight.length; i++) {
            Edge e = new Edge(edge[i << 1], edge[(i << 1) + 1], weight[i]);
            G.addEdge(e);
        }

        KruskalMST mst = new KruskalMST(G);
        System.out.println(mst);
    }
}
