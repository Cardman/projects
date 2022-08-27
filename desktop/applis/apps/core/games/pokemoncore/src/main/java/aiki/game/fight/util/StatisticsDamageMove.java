package aiki.game.fight.util;
import aiki.game.fight.enums.UsefulValueLaw;
import code.maths.Rate;
import code.util.AbsMap;

public final class StatisticsDamageMove {

    private final AbsMap<UsefulValueLaw,Rate> statistics;

    private final String name;

    public StatisticsDamageMove(AbsMap<UsefulValueLaw,Rate> _statistics, String _name) {
        statistics = _statistics;
        name = _name;
    }

    public AbsMap<UsefulValueLaw,Rate> getStatistics() {
        return statistics;
    }

    public String getName() {
        return name;
    }
}
