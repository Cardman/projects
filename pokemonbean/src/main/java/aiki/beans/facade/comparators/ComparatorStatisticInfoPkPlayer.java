package aiki.beans.facade.comparators;
import java.util.Comparator;

import aiki.beans.facade.game.dto.StatisticInfoPkPlayer;

public final class ComparatorStatisticInfoPkPlayer implements Comparator<StatisticInfoPkPlayer> {

    @Override
    public int compare(StatisticInfoPkPlayer _o1, StatisticInfoPkPlayer _o2) {
        return _o1.getName().compareTo(_o2.getName());
    }

}