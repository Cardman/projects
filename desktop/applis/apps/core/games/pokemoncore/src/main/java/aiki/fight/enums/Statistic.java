package aiki.fight.enums;
import code.util.CustList;
import code.util.IdList;
import code.util.core.StringUtil;
import code.util.ints.Listable;

public enum Statistic {
    HP(true, "HP"),
    ATTACK(false, "ATTACK"),
    DEFENSE(false, "DEFENSE"),
    SPECIAL_ATTACK(false, "SPECIAL_ATTACK"),
    SPECIAL_DEFENSE(false, "SPECIAL_DEFENSE"),
    SPEED(true, "SPEED"),
    ACCURACY(true, "ACCURACY"),
    EVASINESS(true, "EVASINESS"),
    CRITICAL_HIT(false, "CRITICAL_HIT"),
    PV_RESTANTS(true, "PV_RESTANTS");
    private final boolean special;
    private final String statName;
    Statistic(boolean _special, String _s) {
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
        base(list_);
        return list_;
    }
    public static IdList<Statistic> getStatisticsWithBoost() {
        IdList<Statistic> list_ = new IdList<Statistic>();
        boost(list_);
        return list_;
    }

    private static void boost(IdList<Statistic> _ls) {
        boostBase(_ls);
        boostNonBase(_ls);
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
        base(ls_);
        boostNonBase(ls_);
        ls_.add(PV_RESTANTS);
        return ls_;
    }

    private static void boostNonBase(CustList<Statistic> _ls) {
        _ls.add(ACCURACY);
        _ls.add(EVASINESS);
        _ls.add(CRITICAL_HIT);
    }

    private static void base(CustList<Statistic> _ls) {
        _ls.add(HP);
        boostBase(_ls);
    }

    private static void boostBase(CustList<Statistic> _ls) {
        _ls.add(ATTACK);
        _ls.add(DEFENSE);
        _ls.add(SPECIAL_ATTACK);
        _ls.add(SPECIAL_DEFENSE);
        _ls.add(SPEED);
    }

    public boolean isSpecial() {
        return special;
    }
}
