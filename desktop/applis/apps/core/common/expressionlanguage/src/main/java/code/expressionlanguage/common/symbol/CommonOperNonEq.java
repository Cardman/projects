package code.expressionlanguage.common.symbol;

import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class CommonOperNonEq implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return BooleanStruct.of(Argument.getNull(_first).sameReference(Argument.getNull(_second))).neg();
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_NON_EQ_PRIO);
    }

}
