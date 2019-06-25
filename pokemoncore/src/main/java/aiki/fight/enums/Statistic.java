package aiki.fight.enums;
import code.util.EnumList;
import code.util.StringList;
import code.util.ints.Listable;

public enum Statistic {
    HP(true,false,true),
    ATTACK(true,true,false),
    DEFENSE(true,true,false),
    SPECIAL_ATTACK(true,true,false),
    SPECIAL_DEFENSE(true,true,false),
    SPEED(true,true,true),
    ACCURACY(false,true,true),
    EVASINESS(false,true,true),
    CRITICAL_HIT(false,true,false),
    PV_RESTANTS(false,false,true);
    private final boolean withBaseStatistic;
    private final boolean boost;
    private final boolean special;
    Statistic(boolean _withBaseStatistic, boolean _boost, boolean _special) {
        withBaseStatistic = _withBaseStatistic;
        boost = _boost;
        special = _special;
    }
    public static boolean equalsSet(Listable<Statistic> _list1,Listable<Statistic> _list2) {
        for (Statistic a: _list2) {
            boolean contains_ = false;
            for (Statistic b: _list1) {
                if (a == b) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (Statistic a: _list1) {
            boolean contains_ = false;
            for (Statistic b: _list2) {
                if (a == b) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }
    public static EnumList<Statistic> getAllStatistics() {
        return new EnumList<Statistic>(values());
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
            if (StringList.quickEq(e.name(), _env)) {
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
