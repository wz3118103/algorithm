package algorithm.math.graph.wz.mst;

public class Edge implements Comparable<Edge> {
    private final int u;
    private final int v;
    private final double weight;

    public Edge(int u, int v, double weight) {
        if (u < 0 || v < 0 || Double.isNaN(weight)) {
            throw new IllegalArgumentException();
        }
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        // 非常重要的知识点，Double的比较
        return Double.compare(this.weight, o.weight);
    }

    public int either() {
        return u;
    }

    public int other(int k) {
        if (k == u) {
            return v;
        }
        if (k == v) {
            return u;
        }
        throw new IllegalArgumentException();
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", u, v, weight);
    }

    public static void main(String[] args) {
        Edge e = new Edge(0, 1, 0.5);
        System.out.println(e);
    }
}
