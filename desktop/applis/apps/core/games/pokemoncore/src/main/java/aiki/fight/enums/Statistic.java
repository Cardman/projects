package aiki.fight.enums;
import code.util.EnumList;
import code.util.core.StringUtil;
import code.util.ints.Listable;

public enum Statistic {
    HP(true,false,true, "HP"),
    ATTACK(true,true,false, "ATTACK"),
    DEFENSE(true,true,false, "DEFENSE"),
    SPECIAL_ATTACK(true,true,false, "SPECIAL_ATTACK"),
    SPECIAL_DEFENSE(true,true,false, "SPECIAL_DEFENSE"),
    SPEED(true,true,true, "SPEED"),
    ACCURACY(false,true,true, "ACCURACY"),
    EVASINESS(false,true,true, "EVASINESS"),
    CRITICAL_HIT(false,true,false, "CRITICAL_HIT"),
    PV_RESTANTS(false,false,true, "PV_RESTANTS");
    private final boolean withBaseStatistic;
    private final boolean boost;
    private final boolean special;
    private final String statName;
    Statistic(boolean _withBaseStatistic, boolean _boost, boolean _special, String _s) {
        withBaseStatistic = _withBaseStatistic;
        boost = _boost;
        special = _special;
        statName = _s;
    }

    public String getStatName() {
        return statName;
    }

    public static boolean equalsSet(Listable<Statistic> _list1, Listable<Statistic> _list2) {
        for (Statistic a: _list2) {
            boolean contains_ = containsStatistic(_list1, a);
            if (!contains_) {
                return false;
            }
        }
        for (Statistic a: _list1) {
            boolean contains_ = containsStatistic(_list2, a);
            if (!contains_) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsStatistic(Listable<Statistic> _list, Statistic _s) {
        boolean contains_ = false;
        for (Statistic b: _list) {
            if (_s == b) {
                contains_ = true;
                break;
            }
        }
        return contains_;
    }

    public static EnumList<Statistic> getStatisticsWithBase() {
        EnumList<Statistic> list_ = new EnumList<Statistic>();
        for (Statistic s: values()) {
            if (!s.withBaseStatistic) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }
    public static EnumList<Statistic> getStatisticsWithBoost() {
        EnumList<Statistic> list_ = new EnumList<Statistic>();
        for (Statistic s: values()) {
            if (!s.boost) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }
    public static Statistic getStatisticByName(String _env) {
        for (Statistic e: values()) {
            if (StringUtil.quickEq(e.statName, _env)) {
                return e;
            }
        }
        return SPEED;
    }
    public boolean isWithBaseStatistic() {
        return withBaseStatistic;
    }
    public boolean isBoost() {
        return boost;
    }

    public boolean isSpecial() {
        return special;
    }
}
