package code.expressionlanguage.utilcompo.common;

import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.MathAdvAliases;
import code.expressionlanguage.utilcompo.RateStruct;
import code.maths.Rate;

public final class CommonOperPlusOneRate implements CommonOperSymbol {
    private final MathAdvAliases aliases;
    public CommonOperPlusOneRate(MathAdvAliases _m) {
        aliases = _m;
    }
    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast) {
        return new RateStruct(Rate.plus(((RateStruct)_first).getRate(),Rate.one()),aliases);
    }
}
