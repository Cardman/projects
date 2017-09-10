package aiki.fight.enums;
import code.serialize.CheckedData;
import code.util.EnumList;
import code.util.ints.Listable;

@CheckedData
public enum Statistic {
    HP(true,false),
    ATTACK(true,true),
    DEFENSE(true,true),
    SPECIAL_ATTACK(true,true),
    SPECIAL_DEFENSE(true,true),
    SPEED(true,true),
    ACCURACY(false,true),
    EVASINESS(false,true),
    CRITICAL_HIT(false,true),
    PV_RESTANTS(false,false);
    private final boolean withBaseStatistic;
    private final boolean boost;
    Statistic(boolean _withBaseStatistic, boolean _boost) {
        withBaseStatistic = _withBaseStatistic;
        boost = _boost;
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
    public boolean isWithBaseStatistic() {
        return withBaseStatistic;
    }
    public boolean isBoost() {
        return boost;
    }
}
