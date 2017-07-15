package aiki.beans.facade.comparators;
import java.util.Comparator;

import aiki.util.Point;

public class ComparatorPoint implements Comparator<Point> {

    @Override
    public int compare(Point _o1, Point _o2) {
        int res_ = _o1.gety() - _o2.gety();
        if (res_ != 0) {
            return res_;
        }
        return _o1.getx() - _o2.getx();
    }

}
