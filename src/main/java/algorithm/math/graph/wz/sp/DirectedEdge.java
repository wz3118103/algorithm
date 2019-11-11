package algorithm.math.graph.wz.sp;

public class DirectedEdge implements Comparable<DirectedEdge> {
    private int v;
    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    @Override
    public String toString() {
        return  v + "->" + w + " " + weight;
    }

    public static void main(String[] args) {
        DirectedEdge edge = new DirectedEdge(0, 1, 0.1);
        System.out.println(edge);
    }

    @Override
    public int compareTo(DirectedEdge o) {
        return Double.compare(this.getWeight(), o.getWeight());
    }
}
