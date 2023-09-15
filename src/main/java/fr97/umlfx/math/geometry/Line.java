package fr97.umlfx.math.geometry;

import java.util.Objects;

/**
 * Simple implementation of Line in 2D integer space
 *
 * @author Filip
 */
public class Line {

    private final Point start;
    private final Point end;

    private Line(final Point start, final Point end) {
        this.start = start;
        this.end = end;
    }

    public static Line of(final Point start, final Point end) {
        if (start == null || end == null)
            throw new IllegalArgumentException("start and end point can't be null");
        return Line.of(start.getX(), start.getY(), end.getX(), end.getY());
    }

    public static Line of(int startX, int startY, int endX, int endY) {
        return new Line(Point.of(startX, startY), Point.of(endX, endY));
    }

    public static Line of(double startX, double startY, double endX, double endY) {
        return new Line(Point.of(startX, startY), Point.of(endX, endY));
    }

    public static Line from(javafx.scene.shape.Line fxLine) {
        return Line.of(fxLine.getStartX(), fxLine.getStartY(), fxLine.getEndX(), fxLine.getEndY());
    }

    public javafx.scene.shape.Line toFXLine() {
        return new javafx.scene.shape.Line(start.getX(), start.getY(), end.getX(), end.getY());
    }

    public Line copy() {
        return new Line(this.start.copy(), this.end.copy());
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public int getStartX() {
        return start.getX();
    }

    public void setStartX(int x) {
        start.setX(x);
    }

    public int getEndX() {
        return end.getX();
    }

    public int getEndY() {
        return end.getY();
    }

    public int getStartY() {
        return start.getY();
    }

    public void setStartY(int y) {
        start.setY(y);
    }


    public double distanceFrom(Point point) {

        /*
         *                      . Point(x,y)
         *                      |
         *                      | - projection line of point to line
         *                      |
         *                      |
         *         Start.-------.-------------------.End
         *               \______| P
         *        0 < projectionDistance < 1
         *
         *
         *                      . Point(x,y)                                    . Point(x,y)
         *                      |                                               |
         *                      |                                               |
         *                      |                                               |
         *                      |                                               |
         *             ---------.----Start.--------------------------.End--------.
         *                   P1 \_________|                        P2 \__________|
         *              projectionDistance1 < 0                      projectionDistance1 > 1
         *
         *              Sometimes projection of point to line is outside of line segment
         *              Then the closest intersection point with line is:
         *
         *              start point of line if projection distance < 0
         *              end point of line if projection distance > 1
         *
         */

        double a = getStartX() - start.getX(); // Vector: Point.x  - Line.startX
        double b = getStartY() - start.getY(); // Vector: Point.y  - Line.starty
        double c = getEndX() - start.getX(); // Vector: Line.endX  - Line.startX
        double d = getEndY() - start.getX(); // Vector: Line.endY  - Line.startY

        double scalar = a * c + b * d; // scalar product of two vectors
        double len_sqr = c * c + d * d; // length of line squared
        double projectionDistance = -1; // projection distance of point to line

        if (len_sqr != 0) {
            projectionDistance = scalar / len_sqr;
        }

        // Coordinates of intersection point
        double xx = 0;
        double yy = 0;

        if (projectionDistance < 0) {
            xx = getStartX();
            yy = getStartY();
        } else if (projectionDistance > 1) {
            xx = getEndX();
            yy = getEndY();
        } else {
            xx = getStartX() + projectionDistance * c;
            yy = getStartY() + projectionDistance * d;
        }

        // Distance between two points
        double dx = point.getX() - xx;
        double dy = point.getY() - yy;
        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (!Objects.equals(start, line.start)) return false;
        return Objects.equals(end, line.end);
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
