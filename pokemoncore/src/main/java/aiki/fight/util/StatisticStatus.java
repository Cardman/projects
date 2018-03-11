package aiki.fight.util;
import aiki.fight.enums.Statistic;
import code.sml.FromAndToString;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class StatisticStatus implements Equallable<StatisticStatus>, Displayable {

    private static final char SEPARATOR = ';';

    private final Statistic statistic;

    private final String status;

    public StatisticStatus(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        statistic = Statistic.getStatisticByName(elements_.first());
        status = elements_.last();
    }

    public StatisticStatus(Statistic _statistic, String _status) {
        statistic = _statistic;
        status = _status;
    }

    @FromAndToString
    public static StatisticStatus newStatisticStatus(String _string) {
        return new StatisticStatus(_string);
    }

    @Override
    public boolean eq(StatisticStatus _obj) {
        if (statistic != _obj.statistic) {
            return false;
        }
        if (!StringList.quickEq(status, _obj.status)) {
            return false;
        }
        return true;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public String getStatus() {
        return status;
    }

    @FromAndToString
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(statistic.name());
        str_.append(SEPARATOR);
        str_.append(status);
        return str_.toString();
    }
}
