package code.expressionlanguage.common.symbol;

import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class CommonOperEq implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return BooleanStruct.of(Argument.getNull(_first).sameReference(Argument.getNull(_second)));
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_EQ_PRIO);
    }
}
