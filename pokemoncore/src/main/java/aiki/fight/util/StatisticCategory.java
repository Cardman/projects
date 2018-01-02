package aiki.fight.util;
import aiki.fight.enums.Statistic;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

@CheckedData
public final class StatisticCategory implements Equallable<StatisticCategory>, Displayable {

    private static final char SEPARATOR = ';';

    private Statistic statistic;

    private String category;

    public StatisticCategory(){
    }

    public StatisticCategory(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        statistic = Statistic.getStatisticByName(elements_.first());
        category = elements_.last();
    }

    public StatisticCategory(Statistic _statistic, String _category) {
        statistic = _statistic;
        category = _category;
    }

    @FromAndToString
    public static StatisticCategory newStatisticCategory(String _string) {
        return new StatisticCategory(_string);
    }

    @Override
    public boolean eq(StatisticCategory _obj) {
        if (statistic != _obj.statistic) {
            return false;
        }
        if (!StringList.quickEq(category, _obj.category)) {
            return false;
        }
        return true;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic _statistic) {
        statistic = _statistic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String _category) {
        category = _category;
    }

    @FromAndToString
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(statistic.name());
        str_.append(SEPARATOR);
        str_.append(category);
        return str_.toString();
    }
}
