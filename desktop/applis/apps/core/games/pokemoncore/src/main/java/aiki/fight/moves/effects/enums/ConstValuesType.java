package aiki.fight.moves.effects.enums;


import aiki.db.*;
import code.util.*;
import code.util.core.*;

public enum ConstValuesType {
    NOTHING(""),LANCEUR_ATTAQUES_TYPES(DataBase.DEF_CONST_VALUES_TYPE_LANCEUR_ATTAQUES_TYPES),TYPES_ATTAQUES_RES(DataBase.DEF_CONST_VALUES_TYPE_TYPES_ATTAQUES_RES);
    private final String cstValType;

    ConstValuesType(String _p) {
        this.cstValType = _p;
    }

    public String getCstValType() {
        return cstValType;
    }

    public static ConstValuesType getConstValuesTypeByName(String _env) {
        for (ConstValuesType e: all()) {
            if (StringUtil.quickEq(e.getCstValType(),_env)) {
                return e;
            }
        }
        return ConstValuesType.NOTHING;
    }
    public static CustList<ConstValuesType> all() {
        CustList<ConstValuesType> ls_ = new CustList<ConstValuesType>();
        ls_.add(NOTHING);
        ls_.add(LANCEUR_ATTAQUES_TYPES);
        ls_.add(TYPES_ATTAQUES_RES);
        return ls_;
    }
}
