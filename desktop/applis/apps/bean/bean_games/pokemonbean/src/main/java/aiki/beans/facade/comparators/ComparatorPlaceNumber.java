package aiki.beans.facade.comparators;
import aiki.map.DataMap;
import aiki.map.places.Place;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorPlaceNumber implements Comparing<Integer> {

    private final DataMap dataMap;

    public ComparatorPlaceNumber(DataMap _dataMap) {
        dataMap = _dataMap;
    }

    @Override
    public int compare(Integer _o1, Integer _o2) {
        Place plOne_ = dataMap.getPlace(_o1);
        Place plTwo_ = dataMap.getPlace(_o2);
        int res_ = StringUtil.compareStrings(plOne_.getName(),plTwo_.getName());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return NumberUtil.compareLg(_o1, _o2);
    }

}