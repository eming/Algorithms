package dp.common;

/**
 * Created by Emin Guliyev on 20/07/2015.
 */
public class Cell {
    public int cost;
    public Decision parent;

    public Cell(int cost, Decision parent) {
        this.cost = cost;
        this.parent = parent;
    }
}