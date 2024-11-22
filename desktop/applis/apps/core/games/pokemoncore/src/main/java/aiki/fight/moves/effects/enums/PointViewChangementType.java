package aiki.fight.moves.effects.enums;


import aiki.db.*;
import code.util.*;
import code.util.core.*;

public enum PointViewChangementType {
    NOTHING(""),THIEF_BONUSES(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_THIEF_BONUSES),MIRROR_AGAINST_THROWER(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_MIRROR_AGAINST_THROWER),ATTRACT_DAMAGES_MOVES(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_ATTRACT_DAMAGES_MOVES);
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
