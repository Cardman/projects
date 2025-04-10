package aiki.fight.util;
import aiki.fight.enums.Statistic;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class StatisticCategory extends AbsStatisticKey implements Displayable {

    private static final char SEPARATOR = ';';

    private String category;

    public StatisticCategory(){
    }

    public StatisticCategory(String _str) {
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        setStatistic(Statistic.getStatisticByName(elements_.first()));
        category = elements_.last();
    }

    public StatisticCategory(Statistic _statistic, String _category) {
        setStatistic(_statistic);
        category = _category;
    }

    
    public static StatisticCategory newStatisticCategory(String _string) {
        return new StatisticCategory(_string);
    }

    public boolean eq(StatisticCategory _obj) {
        if (getStatistic() != _obj.getStatistic()) {
            return false;
        }
        return StringUtil.quickEq(category, _obj.category);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String _category) {
        category = _category;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(getStatistic().getStatName());
        str_.append(SEPARATOR);
        str_.append(category);
        return str_.toString();
    }
}
