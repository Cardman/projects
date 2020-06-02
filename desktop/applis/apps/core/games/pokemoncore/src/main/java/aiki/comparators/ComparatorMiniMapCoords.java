package aiki.comparators;
import aiki.map.util.MiniMapCoords;
import code.util.CustList;
import code.util.*;
import code.util.ints.Comparing;

public final class ComparatorMiniMapCoords implements Comparing<MiniMapCoords> {

    @Override
    public int compare(MiniMapCoords _o1, MiniMapCoords _o2) {
        int res_ = Numbers.compareLg(_o1.getYcoords(), _o2.getYcoords());
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        return Numbers.compareLg(_o1.getXcoords(), _o2.getXcoords());
    }

}
