package aiki.game.fight.util;
import aiki.game.fight.enums.UsefulValueLaw;
import code.maths.Rate;
import code.util.IdMap;

public final class StatisticsDamageMove {

    private final IdMap<UsefulValueLaw,Rate> statistics;

    private final String name;

    public StatisticsDamageMove(IdMap<UsefulValueLaw,Rate> _statistics, String _name) {
        statistics = _statistics;
        name = _name;
    }

    public IdMap<UsefulValueLaw,Rate> getStatistics() {
        return statistics;
    }

    public String getName() {
        return name;
    }
}
