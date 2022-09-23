package aiki.util;

import code.util.CollCapacity;
import code.util.CustList;

public abstract class Points<T> extends CommonMap<Point,T> {
    protected Points() {
    }
    protected Points(CollCapacity _cap) {
        super(_cap);
    }

    public void translateLineData(short _y, short _dir) {
        CustList<Point> links_ = getKeys();
        Points< Point> deplLinks_ = new PointsPoint();
        for (Point c : links_) {
            if (c.gety() < _y) {
                deplLinks_.put(c, c);
                continue;
            }
            deplLinks_.put(c, new Point(c.getx(), (short) (c.gety() + _dir)));
        }
        translate(links_, deplLinks_);
    }

    public void translateColumnData(short _x, short _dir) {
        CustList<Point> links_ = getKeys();
        Points< Point> deplLinks_ = new PointsPoint();
        for (Point c : links_) {
            if (c.getx() < _x) {
                deplLinks_.put(c, c);
                continue;
            }
            deplLinks_.put(c, new Point((short) (c.getx() + _dir), c.gety()));
        }
        translate(links_, deplLinks_);
    }

    private void translate(CustList<Point> _links, Points<Point> _deplLinks) {
        CustList<Point> links_ = _links;
        while (!links_.isEmpty()) {
            for (Point c : links_) {
                Point dest_ = _deplLinks.getVal(c);
                if (!containsPt(links_,dest_)) {
                    T movedBlock_ = getVal(c);
                    removeKey(c);
                    put(dest_, movedBlock_);
                    _deplLinks.removeKey(c);
                }
            }
            for (Point c : links_) {
                if (!_deplLinks.contains(c)) {
                    continue;
                }
                Point dest_ = _deplLinks.getVal(c);
                if (Point.eq(c, dest_)) {
                    _deplLinks.removeKey(c);
                }
            }
            links_ = _deplLinks.getKeys();
        }
    }
    private static boolean containsPt(CustList<Point> _l, Point _pt) {
        for (Point p: _l) {
            if (_pt.eq(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean eq(Point _one, Point _two) {
        return _one.eq(_two);
    }
}
