package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.BitShiftRightOperation;
import code.expressionlanguage.common.NumParsers;

public final class RendBitShiftRightOperation extends RendStdNumericOperation {

    public RendBitShiftRightOperation(BitShiftRightOperation _b) {
        super(_b);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ContextEl _cont) {
        return new Argument(NumParsers.calculateBitShiftRight(NumParsers.convertToNumber(_a.getStruct()),
                NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
    }


}
