package aiki.comparators;
import aiki.db.DataBase;
import aiki.map.places.Place;
import aiki.util.Coords;
import code.util.CustList;
import code.util.*;
import code.util.ints.Comparing;

public final class ComparatorCoords implements Comparing<Coords> {

    private DataBase data;

    public ComparatorCoords(DataBase _data) {
        data = _data;
    }

    @Override
    public int compare(Coords _o1, Coords _o2) {
        Place plOne_ = data.getMap().getPlace(_o1.getNumberPlace());
        Place plTwo_ = data.getMap().getPlace(_o2.getNumberPlace());
        int res_ = plOne_.getName().compareTo(plTwo_.getName());
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        return Numbers.compareLg(_o1.getNumberPlace(), _o2.getNumberPlace());
    }

}
