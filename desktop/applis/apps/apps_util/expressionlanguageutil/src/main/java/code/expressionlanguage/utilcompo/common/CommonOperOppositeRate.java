package code.expressionlanguage.utilcompo.common;

import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.MathAdvAliases;
import code.expressionlanguage.utilcompo.RateStruct;

public final class CommonOperOppositeRate implements CommonOperSymbol {
    private final MathAdvAliases aliases;
    public CommonOperOppositeRate(MathAdvAliases _m) {
        aliases = _m;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast) {
        return new RateStruct(((RateStruct)_first).getRate().opposNb(),aliases);
    }
}
