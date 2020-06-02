package aiki.comparators;
import aiki.game.fight.GroundPlaceKey;
import aiki.game.fight.KeyFightRound;
import code.util.CustList;
import code.util.*;
import code.util.ints.Comparing;

public final class ComparatorGroundPlaceKey implements Comparing<GroundPlaceKey> {

    @Override
    public int compare(GroundPlaceKey _o1, GroundPlaceKey _o2) {
        int res_ = Numbers.compareLg(_o1.getGround(),_o2.getGround());
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        return Numbers.compareLg(_o1.getKey(),_o2.getKey());
    }

}
