package aiki.comparators;
import aiki.game.fight.KeyFightRound;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorFightRound implements Comparing<KeyFightRound> {

    @Override
    public int compare(KeyFightRound _o1, KeyFightRound _o2) {
        int res_ = Numbers.compareLg(_o1.getFight(),_o2.getFight());
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        return Numbers.compareLg(_o1.getRound(),_o2.getRound());
    }

}
