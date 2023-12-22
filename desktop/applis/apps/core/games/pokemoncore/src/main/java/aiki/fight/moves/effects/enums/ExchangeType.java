package aiki.fight.moves.effects.enums;


import code.util.CustList;
import code.util.core.StringUtil;

public enum ExchangeType {
    GIVE_TO_TARGET("0"),GIVE_TO_THROWER("1"),EXCHANGE("2"),GIVE_CONST("3"),NOTHING("");
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
