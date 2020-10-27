package aiki.beans.facade.comparators;
import aiki.beans.facade.map.dto.PlaceIndex;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorPlaceIndex implements Comparing<PlaceIndex> {

    @Override
    public int compare(PlaceIndex _arg0, PlaceIndex _arg1) {
        return StringUtil.compareStrings(_arg0.getPlace().getName(),_arg1.getPlace().getName());
    }

}