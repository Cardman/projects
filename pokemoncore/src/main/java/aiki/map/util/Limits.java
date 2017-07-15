package aiki.map.util;
import aiki.util.Point;

public final class Limits {

    private final Point topLeft;

    private final Point bottomRight;

    public Limits(Point _topLeft, Point _bottomRight) {
        topLeft = _topLeft;
        bottomRight = _bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }
}
