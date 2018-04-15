package aiki.comparators;
import java.util.Comparator;

import aiki.DataBase;
import aiki.map.places.Place;
import aiki.util.Coords;
import code.util.CustList;
import code.util.Numbers;

public final class ComparatorCoords implements Comparator<Coords> {

    private DataBase data;

    public ComparatorCoords(DataBase _data) {
        data = _data;
    }

    @Override
    public int compare(Coords _o1, Coords _o2) {
        Place plOne_ = data.getMap().getPlaces().getVal(_o1.getNumberPlace());
        Place plTwo_ = data.getMap().getPlaces().getVal(_o2.getNumberPlace());
        int res_ = plOne_.getName().compareTo(plTwo_.getName());
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        return Numbers.compare(_o1.getNumberPlace(), _o2.getNumberPlace());
    }

}
