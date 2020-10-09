package aiki.comparators;
import aiki.game.fight.KeyFightRound;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorFightRound implements Comparing<KeyFightRound> {

    @Override
    public int compare(KeyFightRound _o1, KeyFightRound _o2) {
        int res_ = NumberUtil.compareLg(_o1.getFight(),_o2.getFight());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return NumberUtil.compareLg(_o1.getRound(),_o2.getRound());
    }

}
