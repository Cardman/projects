package aiki.fight.moves.effects.enums;


import code.util.CustList;
import code.util.core.StringUtil;

public enum PointViewChangementType {
    NOTHING(""),THIEF_BONUSES("0"),MIRROR_AGAINST_THROWER("1"),ATTRACT_DAMAGES_MOVES("2");
    private final String ptView;

    PointViewChangementType(String _p) {
        this.ptView = _p;
    }

    public String getPtView() {
        return ptView;
    }

    public static PointViewChangementType getPointViewChangementTypeByName(String _env) {
        for (PointViewChangementType e: all()) {
            if (StringUtil.quickEq(e.getPtView(),_env)) {
                return e;
            }
        }
        return PointViewChangementType.NOTHING;
    }
    public static CustList<PointViewChangementType> all() {
        CustList<PointViewChangementType> ls_ = new CustList<PointViewChangementType>();
        ls_.add(NOTHING);
        ls_.add(THIEF_BONUSES);
        ls_.add(MIRROR_AGAINST_THROWER);
        ls_.add(ATTRACT_DAMAGES_MOVES);
        return ls_;
    }
}
