package aiki.beans.facade.comparators;
import aiki.util.Point;
import code.util.ints.Comparing;

public final class ComparatorPoint implements Comparing<Point> {

    @Override
    public int compare(Point _o1, Point _o2) {
        int res_ = _o1.gety() - _o2.gety();
        if (res_ != 0) {
            return res_;
        }
        return _o1.getx() - _o2.getx();
    }

}