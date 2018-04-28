package aiki.beans.facade.comparators;
import code.util.ints.Comparing;

import aiki.beans.facade.map.dto.PlaceIndex;

public final class ComparatorPlaceIndex implements Comparing<PlaceIndex> {

    @Override
    public int compare(PlaceIndex _arg0, PlaceIndex _arg1) {
        return _arg0.getPlace().getName().compareTo(_arg1.getPlace().getName());
    }

}