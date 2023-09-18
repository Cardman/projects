package code.expressionlanguage.utilcompo.common;

import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.common.symbol.SymbolConstants;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.Struct;

public final class CommonOperSameRate implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return _first;
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_ID_PRIO)+"/"+ PrimitiveTypes.RATE;
    }

}
