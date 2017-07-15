package aiki.beans.facade.comparators;
import java.util.Comparator;

import code.util.CustList;
import code.util.Numbers;
import aiki.map.DataMap;
import aiki.map.places.Place;

public class ComparatorPlaceNumber implements Comparator<Short> {

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
        return Numbers.compare(_o1, _o2);
    }

}
