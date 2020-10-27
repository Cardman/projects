package aiki.beans.facade.comparators;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayer;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorStatisticInfoPkPlayer implements Comparing<StatisticInfoPkPlayer> {

    @Override
    public int compare(StatisticInfoPkPlayer _o1, StatisticInfoPkPlayer _o2) {
        return StringUtil.compareStrings(_o1.getName(),_o2.getName());
    }

}