package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.BitShiftLeftOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NumberStruct;

public final class RendBitShiftLeftOperation extends RendStdNumericOperation {

    public RendBitShiftLeftOperation(BitShiftLeftOperation _b) {
        super(_b);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ContextEl _cont) {
        return new Argument(NumberStruct.calculateBitShiftLeft(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), _cont, getResultClass()));
    }

}
