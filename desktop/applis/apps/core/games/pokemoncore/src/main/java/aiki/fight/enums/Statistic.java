package aiki.fight.enums;
import code.util.CustList;
import code.util.IdList;
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
        return containsAll(_list1, _list2) && containsAll(_list2, _list1);
    }

    public static boolean containsAll(Listable<Statistic> _container, Listable<Statistic> _content) {
        for (Statistic a: _content) {
            boolean contains_ = containsStatistic(_container, a);
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

    public static IdList<Statistic> getStatisticsWithBase() {
        IdList<Statistic> list_ = new IdList<Statistic>();
        for (Statistic s: all()) {
            if (!s.isWithBaseStatistic()) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }
    public static IdList<Statistic> getStatisticsWithBoost() {
        IdList<Statistic> list_ = new IdList<Statistic>();
        for (Statistic s: all()) {
            if (!s.isBoost()) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }
    public static Statistic getStatisticByName(String _env) {
        for (Statistic e: all()) {
            if (StringUtil.quickEq(e.statName, _env)) {
                return e;
            }
        }
        return SPEED;
    }
    public static CustList<Statistic> all() {
        CustList<Statistic> ls_ = new CustList<Statistic>();
        ls_.add(HP);
        ls_.add(ATTACK);
        ls_.add(DEFENSE);
        ls_.add(SPECIAL_ATTACK);
        ls_.add(SPECIAL_DEFENSE);
        ls_.add(SPEED);
        ls_.add(ACCURACY);
        ls_.add(EVASINESS);
        ls_.add(CRITICAL_HIT);
        ls_.add(PV_RESTANTS);
        return ls_;
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
