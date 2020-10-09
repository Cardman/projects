package aiki.fight.util;
import aiki.fight.enums.Statistic;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class StatisticType implements Equallable<StatisticType>, Displayable {

    private static final char SEPARATOR = ';';

    private Statistic statistic;

    private String type;

    public StatisticType(){
    }

    public StatisticType(String _str) {
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        statistic = Statistic.getStatisticByName(elements_.first());
        type = elements_.last();
    }

    public StatisticType(Statistic _statistic, String _type) {
        statistic = _statistic;
        type = _type;
    }

    
    public static StatisticType newStatisticType(String _string) {
        return new StatisticType(_string);
    }

    @Override
    public boolean eq(StatisticType _obj) {
        if (statistic != _obj.statistic) {
            return false;
        }
        return StringUtil.quickEq(type, _obj.type);
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic _statistic) {
        statistic = _statistic;
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(statistic.name());
        str_.append(SEPARATOR);
        str_.append(type);
        return str_.toString();
    }
}
