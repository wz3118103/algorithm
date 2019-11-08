package algorithm.math.graph.wz.mst;

import datastructure.queueandheap.wz.IndexMinPriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 核心思想：
 * 1）只用记住每个不在树中的顶点到树的最短距离，并将这些距离放在优先队列中
 * 2）每次从优先队列中取出（点、边）是，在必要的情况下需要对剩余的（点、边）进行松弛
 *
 * 有了IndexMinPQ代码极其精简
 */
public class PrimMST {
    private static final String NEWLINE = System.getProperty("line.separator");

    private EdgeWeightedGraph G;
    private boolean[] marked;
    private ArrayList<Edge> mst;
    // distTo[i] 表示顶点i到已生成的部分mst树的最短距离
    private double[] distTo;
    // 保存distTo对应的横切边
    private Edge[] edgeTo;
    // 对(i, distTo[i])进行堆化
    private IndexMinPriorityQueue<Double> pq;

    public PrimMST(EdgeWeightedGraph g) {
        G = g;
        marked = new boolean[g.V()];
        mst = new ArrayList<>();
        distTo = new double[g.V()];
        Arrays.fill(distTo, Double.MAX_VALUE);
        edgeTo = new Edge[g.V()];
        pq = new IndexMinPriorityQueue<>(g.V());

        prim();
    }

    private void prim() {
        // 注意更新一下distTo[0]
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while(!pq.isEmpty()) {
            int v = pq.delMin();
            marked[v] = true;
            if (v != 0) {
                mst.add(edgeTo[v]);
            }
            for (Edge e : G.adj(v)) {
                int w = e.other(v);
                if (!marked[w]) {
                    if (distTo[w] > e.getWeight()) {
                        distTo[w] = e.getWeight();
                        edgeTo[w] = e;
                        // 先判断是否包含
                        if (pq.contains(w)) {
                            pq.change(w, distTo[w]);
                        } else {
                            pq.insert(w, distTo[w]);
                        }
                    }
                }
            }
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

        PrimMST mst = new PrimMST(G);
        System.out.println(mst);
    }
}
