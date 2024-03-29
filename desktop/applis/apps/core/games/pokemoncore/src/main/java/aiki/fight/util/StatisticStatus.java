package aiki.fight.util;
import aiki.fight.enums.Statistic;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class StatisticStatus implements Displayable {

    private static final char SEPARATOR = ';';

    private final Statistic statistic;

    private final String status;

    public StatisticStatus(String _str) {
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        statistic = Statistic.getStatisticByName(elements_.first());
        status = elements_.last();
    }

    public StatisticStatus(Statistic _statistic, String _status) {
        statistic = _statistic;
        status = _status;
    }

    
    public static StatisticStatus newStatisticStatus(String _string) {
        return new StatisticStatus(_string);
    }

    public boolean eq(StatisticStatus _obj) {
        if (statistic != _obj.statistic) {
            return false;
        }
        return StringUtil.quickEq(status, _obj.status);
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public String getStatus() {
        return status;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(statistic.getStatName());
        str_.append(SEPARATOR);
        str_.append(status);
        return str_.toString();
    }
}
