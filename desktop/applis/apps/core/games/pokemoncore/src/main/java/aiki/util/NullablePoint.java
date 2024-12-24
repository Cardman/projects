package aiki.util;

import code.util.*;

public final class NullablePoint {
    private Point point;

    public NullablePoint() {
    }

    public NullablePoint(Point _pt) {
        point = _pt;
    }

    public NullablePoint(String _string) {
        if (_string.trim().isEmpty()) {
            return;
        }
        setPoint(new Point(_string));
    }
    public Point value() {
        if (isDefined()) {
            return point;
        }
        return new Point();
    }
    public boolean patchValid() {
        if (!isDefined()) {
            setPoint(new Point());
            return false;
        }
        return true;
    }
    public boolean isDefined() {
        return point != null;
    }
    public static void tryAdd(CustList<Point> _ls, NullablePoint _n) {
        if (_n.isDefined()) {
            _ls.add(_n.getPoint());
        }
    }
    public static void tryAdd(Points<int[][]> _ls, NullablePoint _n, int[][] _v) {
        if (_n.isDefined()) {
            _ls.put(_n.getPoint(), _v);
        }
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point _p) {
        this.point = _p;
    }

    public String display() {
        if (point == null) {
            return "";
        }
        return point.display();
    }
}
