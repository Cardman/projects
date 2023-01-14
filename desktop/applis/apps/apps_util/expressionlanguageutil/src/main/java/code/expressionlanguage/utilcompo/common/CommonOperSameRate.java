package code.expressionlanguage.utilcompo.common;

import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.structs.Struct;

public final class CommonOperSameRate implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast) {
        return _first;
    }
}
