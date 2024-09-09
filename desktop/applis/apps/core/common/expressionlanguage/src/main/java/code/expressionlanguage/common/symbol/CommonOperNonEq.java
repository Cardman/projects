package code.expressionlanguage.common.symbol;

import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class CommonOperNonEq implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return BooleanStruct.of(ArgumentListCall.getNull(_first).sameReference(ArgumentListCall.getNull(_second))).neg();
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_NON_EQ_PRIO);
    }

}
