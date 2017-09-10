package aiki.fight.util;
import aiki.fight.enums.Statistic;
import code.serialize.CheckedData;
import code.util.StringList;
import code.util.ints.Equallable;
import code.xml.FromAndToString;

@CheckedData
public final class StatisticType implements Equallable<StatisticType> {

    private static final char SEPARATOR = ';';

    private Statistic statistic;

    private String type;

    public StatisticType(){
    }

    public StatisticType(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        statistic = Statistic.valueOf(elements_.first());
        type = elements_.last();
    }

    public StatisticType(Statistic _statistic, String _type) {
        statistic = _statistic;
        type = _type;
    }

    @FromAndToString
    public static StatisticType newStatisticType(String _string) {
        return new StatisticType(_string);
    }

    @Override
    public boolean eq(StatisticType _obj) {
        if (statistic != _obj.statistic) {
            return false;
        }
        if (!StringList.quickEq(type, _obj.type)) {
            return false;
        }
        return true;
    }

    @FromAndToString
    @Override
    public String toString() {
        return statistic.toString()+SEPARATOR+type;
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
}
