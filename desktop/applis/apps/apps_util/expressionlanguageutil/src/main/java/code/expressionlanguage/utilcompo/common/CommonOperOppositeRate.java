package code.expressionlanguage.utilcompo.common;

import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.common.symbol.SymbolConstants;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RateStruct;

public final class CommonOperOppositeRate implements CommonOperSymbol {

    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return new RateStruct(((RateStruct)_first).getRate().opposNb());
    }
    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_OPPOSITE_PRIO)+"/"+ PrimitiveTypes.RATE;
    }

}
