package graph.common;

/**
 * Created by Emin Guliyev on 19/07/2015.
 */
public class Edge implements Comparable<Edge>{
    public int from;
    public int to;
    public double weight;

    public Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(weight, other.weight);
    }
}
