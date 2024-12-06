package aiki.fight.util;
import aiki.fight.enums.Statistic;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class StatisticStatus extends AbsStatisticKey implements Displayable {

    private static final char SEPARATOR = ';';

    private String status;

    public StatisticStatus(String _str) {
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        setStatistic(Statistic.getStatisticByName(elements_.first()));
        status = elements_.last();
    }

    public StatisticStatus(Statistic _statistic, String _status) {
        setStatistic(_statistic);
        status = _status;
    }

    
    public static StatisticStatus newStatisticStatus(String _string) {
        return new StatisticStatus(_string);
    }

    public boolean eq(StatisticStatus _obj) {
        if (getStatistic() != _obj.getStatistic()) {
            return false;
        }
        return StringUtil.quickEq(status, _obj.status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String _s) {
        this.status = _s;
    }
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(getStatistic().getStatName());
        str_.append(SEPARATOR);
        str_.append(status);
        return str_.toString();
    }
}
