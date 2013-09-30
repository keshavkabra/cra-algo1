import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new slopeOrder();       // YOUR DEFINITION HERE
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    private class slopeOrder implements Comparator<Point>{
    	public int compare( Point x1, Point x2){
    		return Double.compare( slopeTo(x1), slopeTo(x2));
    	}
    }
    
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	if ( this.x == that.x )
    		if( this.y == that.y )
    			return Double.NEGATIVE_INFINITY;
    		else
    			return Double.POSITIVE_INFINITY;
    	else
    		if ( this.y == that.y )
    			return 0.0;
    		else
    			return (double)(that.y - this.y)/( that.x - this.x );
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	if ( this.y == that.y )
    		return -this.x + that.x;
    	return -this.y + that.y;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}