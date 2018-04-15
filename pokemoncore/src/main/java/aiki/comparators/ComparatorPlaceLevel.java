package aiki.comparators;
import java.util.Comparator;

import aiki.map.util.PlaceLevel;
import code.util.CustList;
import code.util.Numbers;

public final class ComparatorPlaceLevel implements Comparator<PlaceLevel> {

    @Override
    public int compare(PlaceLevel _o1, PlaceLevel _o2) {
        int res_ = Numbers.compare(_o1.getPlace(),_o2.getPlace());
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        return Numbers.compare(_o1.getLevel(),_o2.getLevel());
    }

}
