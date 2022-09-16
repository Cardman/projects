package aiki.game.fight;
import aiki.game.fight.enums.UsefulValueLaw;
import aiki.game.fight.util.StatisticsDamageMove;
import code.maths.Rate;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorLaws implements Comparing<StatisticsDamageMove> {

    @Override
    public int compare(StatisticsDamageMove _o1,
            StatisticsDamageMove _o2) {
        Rate resLoc_ = Rate.minus(_o2.getStatistics().getVal(UsefulValueLaw.MINI), _o1.getStatistics().getVal(UsefulValueLaw.MINI));
        if (!resLoc_.isZero()) {
            return (int) resLoc_.signumToLong();
        }
        resLoc_ = Rate.minus(_o2.getStatistics().getVal(UsefulValueLaw.MAXI), _o1.getStatistics().getVal(UsefulValueLaw.MAXI));
        if (!resLoc_.isZero()) {
            return (int) resLoc_.signumToLong();
        }
        resLoc_ = Rate.minus(_o2.getStatistics().getVal(UsefulValueLaw.MOY), _o1.getStatistics().getVal(UsefulValueLaw.MOY));
        if (!resLoc_.isZero()) {
            return (int) resLoc_.signumToLong();
        }
        resLoc_ = Rate.minus(_o2.getStatistics().getVal(UsefulValueLaw.VAR), _o1.getStatistics().getVal(UsefulValueLaw.VAR));
        if (!resLoc_.isZero()) {
            return (int) -resLoc_.signumToLong();
        }
        return StringUtil.compareStrings(_o2.getName(),_o1.getName());
    }

}
