package graph.common;

/**
 * Created by Emin Guliyev on 18/07/2015.
 */
public class AdjacentNode {
    public int to;
    //for max flow weight means residual
    public double weight;

    public AdjacentNode(int to, double weight) {
        this.to = to;
        this.weight = weight;
    }
}
