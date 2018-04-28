package aiki.beans.facade.comparators;
import code.util.ints.Comparing;

import aiki.beans.facade.game.dto.StatisticInfoPkPlayer;

public final class ComparatorStatisticInfoPkPlayer implements Comparing<StatisticInfoPkPlayer> {

    @Override
    public int compare(StatisticInfoPkPlayer _o1, StatisticInfoPkPlayer _o2) {
        return _o1.getName().compareTo(_o2.getName());
    }

}