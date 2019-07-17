package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.ShiftRightOperation;
import code.expressionlanguage.structs.NumberStruct;

public final class RendShiftRightOperation extends RendStdNumericOperation {

    public RendShiftRightOperation(ShiftRightOperation _b) {
        super(_b);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ExecutableCode _cont) {
        return new Argument(NumberStruct.calculateShiftRight((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, getResultClass()));
    }


}
