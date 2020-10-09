package aiki.comparators;
import aiki.map.util.MiniMapCoords;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorMiniMapCoords implements Comparing<MiniMapCoords> {

    @Override
    public int compare(MiniMapCoords _o1, MiniMapCoords _o2) {
        int res_ = NumberUtil.compareLg(_o1.getYcoords(), _o2.getYcoords());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return NumberUtil.compareLg(_o1.getXcoords(), _o2.getXcoords());
    }

}
