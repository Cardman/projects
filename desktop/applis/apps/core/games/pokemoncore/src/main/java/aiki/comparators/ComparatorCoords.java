package aiki.comparators;
import aiki.db.DataBase;
import aiki.map.places.Place;
import aiki.util.Coords;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
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
        int res_ = StringUtil.compareStrings(plOne_.getName(),plTwo_.getName());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return NumberUtil.compareLg(_o1.getNumberPlace(), _o2.getNumberPlace());
    }

}
