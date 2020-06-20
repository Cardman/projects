package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.ShiftRightOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NumberStruct;

public final class RendShiftRightOperation extends RendStdNumericOperation {

    public RendShiftRightOperation(ShiftRightOperation _b) {
        super(_b);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ContextEl _cont) {
        return new Argument(NumberStruct.calculateShiftRight(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), _cont, getResultClass()));
    }


}
