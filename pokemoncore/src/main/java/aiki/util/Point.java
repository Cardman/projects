package aiki.util;
import aiki.map.enums.Direction;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Equallable;

@CheckedData
public final class Point implements Equallable<Point> {

    static final char SEPARATOR = ',';

    private static final String EMPTY_STRING = "";

    private short xCoord;

    private short yCoord;

    public Point() {
    }

    public Point(short _xCoord, short _yCoord) {
        xCoord = _xCoord;
        yCoord = _yCoord;
    }

    public Point(Point _p) {
        xCoord = _p.getx();
        yCoord = _p.gety();
    }

    public Point(String _string) {
        StringList elements_ = StringList.splitChars(_string, SEPARATOR);
        setx(Short.parseShort(elements_.first()));
        sety(Short.parseShort(elements_.last()));
    }

    @FromAndToString
    public static Point newPoint(String _string) {
        return new Point(_string);
    }

    public void affect(Point _p) {
        setx(_p.getx());
        sety(_p.gety());
    }

    public static boolean eq(Point _pt1,Point _pt2) {
        if (_pt1 == null) {
            return _pt2 == null;
        }
        return _pt1.eq(_pt2);
    }

    @Override
    public boolean eq(Point _obj) {
        if (!Numbers.eq(xCoord, _obj.xCoord)) {
            return false;
        }
        if (!Numbers.eq(yCoord, _obj.yCoord)) {
            return false;
        }
        return true;
    }

    public short getx() {
        return xCoord;
    }

    public void moveTo(Direction _pt) {
        setx((short) (getx()+_pt.getx()));
        sety((short) (gety()+_pt.gety()));
    }

    public void setx(short _x) {
        xCoord = _x;
    }

    public short gety() {
        return yCoord;
    }

    public void sety(short _y) {
        yCoord = _y;
    }

    @Override
    @FromAndToString
    public String toString() {
        return EMPTY_STRING+getx()+SEPARATOR+gety();
    }
}
