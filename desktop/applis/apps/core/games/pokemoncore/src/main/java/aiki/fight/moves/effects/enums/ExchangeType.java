package aiki.fight.moves.effects.enums;


import aiki.db.*;
import code.util.*;
import code.util.core.*;

public enum ExchangeType {
    GIVE_TO_TARGET(DataBase.DEF_EXCHANGE_TYPE_GIVE_TO_TARGET),GIVE_TO_THROWER(DataBase.DEF_EXCHANGE_TYPE_GIVE_TO_THROWER),EXCHANGE(DataBase.DEF_EXCHANGE_TYPE_EXCHANGE),GIVE_CONST(DataBase.DEF_EXCHANGE_TYPE_GIVE_CONST),NOTHING("");
    private final String excType;

    ExchangeType(String _p) {
        this.excType = _p;
    }

    public String getExcType() {
        return excType;
    }

    public static ExchangeType getExchangeTypeByName(String _env) {
        for (ExchangeType e: all()) {
            if (StringUtil.quickEq(e.getExcType(),_env)) {
                return e;
            }
        }
        return ExchangeType.NOTHING;
    }
    public static CustList<ExchangeType> all() {
        CustList<ExchangeType> ls_ = new CustList<ExchangeType>();
        ls_.add(NOTHING);
        ls_.add(GIVE_TO_TARGET);
        ls_.add(GIVE_TO_THROWER);
        ls_.add(EXCHANGE);
        ls_.add(GIVE_CONST);
        return ls_;
    }
}
