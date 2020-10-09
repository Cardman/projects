package aiki.comparators;
import aiki.game.fight.GroundPlaceKey;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorGroundPlaceKey implements Comparing<GroundPlaceKey> {

    @Override
    public int compare(GroundPlaceKey _o1, GroundPlaceKey _o2) {
        int res_ = NumberUtil.compareLg(_o1.getGround(),_o2.getGround());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return NumberUtil.compareLg(_o1.getKey(),_o2.getKey());
    }

}
