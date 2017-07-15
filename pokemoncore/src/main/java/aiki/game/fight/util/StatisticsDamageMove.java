package aiki.game.fight.util;
import code.maths.Rate;
import code.util.EnumMap;
import aiki.game.fight.enums.UsefulValueLaw;

public final class StatisticsDamageMove {

    private final EnumMap<UsefulValueLaw,Rate> statistics;

    private final String name;

    public StatisticsDamageMove(EnumMap<UsefulValueLaw,Rate> _statistics, String _name) {
        statistics = _statistics;
        name = _name;
    }

    public EnumMap<UsefulValueLaw,Rate> getStatistics() {
        return statistics;
    }

    public String getName() {
        return name;
    }
}
