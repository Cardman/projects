package aiki.util;

import aiki.map.enums.Direction;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class Point {

    static final char SEPARATOR = ',';

    private int xCoord;

    private int yCoord;

    public Point() {
    }

    public Point(int _xCoord, int _yCoord) {
        xCoord = _xCoord;
        yCoord = _yCoord;
    }

    public Point(Point _p) {
        xCoord = _p.getx();
        yCoord = _p.gety();
    }

    public Point(String _string) {
        StringList elements_ = StringUtil.splitChars(_string, SEPARATOR);
        setx(NumberUtil.parseInt(elements_.first()));
        sety(NumberUtil.parseInt(elements_.last()));
    }

    
    public static Point newPoint(String _string) {
        return new Point(_string);
    }

    public void affect(Point _p) {
        setx(_p.getx());
        sety(_p.gety());
    }

    public static boolean eq(NullablePoint _pt1,Point _pt2) {
        if (!_pt1.isDefined()) {
            return false;
        }
        return _pt1.getPoint().eq(_pt2);
    }

    public static boolean eq(Point _pt1,Point _pt2) {
        return _pt1.eq(_pt2);
    }

    public boolean eq(Point _obj) {
        if (!NumberUtil.eq(xCoord, _obj.xCoord)) {
            return false;
        }
        return NumberUtil.eq(yCoord, _obj.yCoord);
    }

    public int getx() {
        return xCoord;
    }

    public void moveTo(Direction _pt) {
        setx(getx()+_pt.getx());
        sety(gety()+_pt.gety());
    }

    public void setx(int _x) {
        xCoord = _x;
    }

    public int gety() {
        return yCoord;
    }

    public void sety(int _y) {
        yCoord = _y;
    }

    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(getx());
        str_.append(SEPARATOR);
        str_.append(gety());
        return str_.toString();
    }
}
