package aiki.comparators;
import java.util.Comparator;

import aiki.map.util.ScreenCoords;
import code.util.CustList;

public final class ComparatorScreenCoords implements Comparator<ScreenCoords> {

    @Override
    public int compare(ScreenCoords _o1,
            ScreenCoords _o2) {
        int cmp_ = _o1.getXcoords() - _o2.getXcoords();
        if (cmp_ != CustList.EQ_CMP) {
            return cmp_;
        }
        return _o1.getYcoords() - _o2.getYcoords();
    }

}
