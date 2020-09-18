package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.BitOrOperation;
import code.expressionlanguage.common.NumParsers;

public final class RendBitOrOperation extends RendStdNumericOperation {

    public RendBitOrOperation(BitOrOperation _b) {
        super(_b);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ContextEl _cont) {
        return new Argument(NumParsers.calculateOr(_a.getStruct(), _b.getStruct(), getResultClass().getUnwrapObjectNb()));
    }

}
