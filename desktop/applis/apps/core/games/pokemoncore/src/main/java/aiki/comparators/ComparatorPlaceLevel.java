package aiki.comparators;
import aiki.map.util.PlaceLevel;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorPlaceLevel implements Comparing<PlaceLevel> {

    @Override
    public int compare(PlaceLevel _o1, PlaceLevel _o2) {
        int res_ = NumberUtil.compareLg(_o1.getPlace(),_o2.getPlace());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return NumberUtil.compareLg(_o1.getLevel(),_o2.getLevel());
    }

}
