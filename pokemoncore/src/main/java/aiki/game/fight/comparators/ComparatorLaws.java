package aiki.game.fight.comparators;
import java.util.Comparator;

import code.maths.Rate;
import aiki.game.fight.enums.UsefulValueLaw;
import aiki.game.fight.util.StatisticsDamageMove;

public final class ComparatorLaws implements Comparator<StatisticsDamageMove> {

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
        return _o2.getName().compareTo(_o1.getName());
    }

}
