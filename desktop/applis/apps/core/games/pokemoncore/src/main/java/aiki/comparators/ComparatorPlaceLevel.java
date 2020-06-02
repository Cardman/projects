package aiki.comparators;
import aiki.map.util.PlaceLevel;
import code.util.CustList;
import code.util.*;
import code.util.ints.Comparing;

public final class ComparatorPlaceLevel implements Comparing<PlaceLevel> {

    @Override
    public int compare(PlaceLevel _o1, PlaceLevel _o2) {
        int res_ = Numbers.compareLg(_o1.getPlace(),_o2.getPlace());
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        return Numbers.compareLg(_o1.getLevel(),_o2.getLevel());
    }

}
