package geometry;

/*************************************************************************
 *  Compilation:  javac Point2D.java
 *  Execution:    java Point2D x0 y0 N
 *  Dependencies: StdDraw.java StdRandom.java
 *
 *  Immutable point data type for points in the plane.
 *
 *************************************************************************/
import java.util.Comparator;


/**
 *  The <tt>Point</tt> class is an immutable data type to encapsulate a
 *  two-dimensional point with real-value coordinates.
 *  <p>
 *  Note: in order to deal with the difference behavior of double and
 *  Double with respect to -0.0 and +0.0, the Point2D constructor converts
 *  any coordinates that are -0.0 to +0.0.
 *
 *  For additional documentation, see <a href="/algs4/12oop">Section 1.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public final class Point2D implements Comparable<Point2D> {

    public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
    private final double x;    // x coordinate
    private final double y;    // y coordinate

    /**
     * Initializes a new point (x, y).
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @throws IllegalArgumentException if either <tt>x</tt> or <tt>y</tt>
     *    is <tt>Double.NaN</tt>, <tt>Double.POSITIVE_INFINITY</tt> or
     *    <tt>Double.NEGATIVE_INFINITY</tt>
     */
    public Point2D(double x, double y) {
        if (Double.isInfinite(x) || Double.isInfinite(y))
            throw new IllegalArgumentException("Coordinates must be finite");
        if (Double.isNaN(x) || Double.isNaN(y))
            throw new IllegalArgumentException("Coordinates cannot be NaN");
        if (x == 0.0) this.x = 0.0;  // convert -0.0 to +0.0
        else          this.x = x;

        if (y == 0.0) this.y = 0.0;  // convert -0.0 to +0.0
        else          this.y = y;
    }

    /**
     * Returns the x-coordinate.
     * @return the x-coordinate
     */
    public double x() {
        return x;
    }

    /**
     * Returns the y-coordinate.
     * @return the y-coordinate
     */
    public double y() {
        return y;
    }
    /**
     * Is a->b->c a counterclockwise turn?
     * @param a first point
     * @param b second point
     * @param c third point
     * @return { -1, 0, +1 } if a->b->c is a { clockwise, collinear; counterclocwise } turn.
     */
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
        if      (area2 < 0) return -1;
        else if (area2 > 0) return +1;
        else                return  0;
    }

    /**
     * Compares this point to that point by y-coordinate, breaking ties by x-coordinate.
     * @param that the other point
     * @return { a negative integer, zero, a positive integer } if this point is
     *    { less than, equal to, greater than } that point
     */
    public int compareTo(Point2D that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    // compare other points relative to polar angle (between 0 and 2pi) they make with this Point
    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double dx1 = q1.x - x;
            double dy1 = q1.y - y;
            double dx2 = q2.x - x;
            double dy2 = q2.y - y;

            if      (dy1 >= 0 && dy2 < 0) return -1;    // q1 above; q2 below
            else if (dy2 >= 0 && dy1 < 0) return +1;    // q1 below; q2 above
            else if (dy1 == 0 && dy2 == 0) {            // 3-collinear and horizontal
                if      (dx1 >= 0 && dx2 < 0) return -1;
                else if (dx2 >= 0 && dx1 < 0) return +1;
                else                          return  0;
            }
            else return -ccw(Point2D.this, q1, q2);     // both above or below

            // Note: ccw() recomputes dx1, dy1, dx2, and dy2
        }
    }

    /**
     * Does this point equal y?
     * @param other the other point
     * @return true if this point equals the other point; false otherwise
     */
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Point2D that = (Point2D) other;
        return this.x == that.x && this.y == that.y;
    }

    /**
     * Return a string representation of this point.
     * @return a string representation of this point in the format (x, y)
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Returns an integer hash code for this point.
     * @return an integer hash code for this point
     */
    public int hashCode() {
        int hashX = ((Double) x).hashCode();
        int hashY = ((Double) y).hashCode();
        return 31*hashX + hashY;
    }

}