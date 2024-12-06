package aiki.fight.util;
import aiki.fight.enums.Statistic;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class StatisticType extends AbsStatisticKey implements Displayable {

    private static final char SEPARATOR = ';';

    private String type;

    public StatisticType(){
    }

    public StatisticType(String _str) {
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        setStatistic(Statistic.getStatisticByName(elements_.first()));
        type = elements_.last();
    }

    public StatisticType(Statistic _statistic, String _type) {
        setStatistic(_statistic);
        type = _type;
    }

    
    public static StatisticType newStatisticType(String _string) {
        return new StatisticType(_string);
    }

    public boolean eq(StatisticType _obj) {
        if (getStatistic() != _obj.getStatistic()) {
            return false;
        }
        return StringUtil.quickEq(type, _obj.type);
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
        str_.append(getStatistic().getStatName());
        str_.append(SEPARATOR);
        str_.append(type);
        return str_.toString();
    }
}
