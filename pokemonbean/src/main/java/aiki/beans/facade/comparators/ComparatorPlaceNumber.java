package aiki.beans.facade.comparators;
import aiki.map.DataMap;
import aiki.map.places.Place;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorPlaceNumber implements Comparing<Short> {

    private DataMap dataMap;

    public ComparatorPlaceNumber(DataMap _dataMap) {
        dataMap = _dataMap;
    }

    @Override
    public int compare(Short _o1, Short _o2) {
        Place plOne_ = dataMap.getPlaces().getVal(_o1);
        Place plTwo_ = dataMap.getPlaces().getVal(_o2);
        int res_ = plOne_.getName().compareTo(plTwo_.getName());
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        return Numbers.compareLg(_o1, _o2);
    }

}