package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.ShiftLeftOperation;
import code.expressionlanguage.structs.NumberStruct;

public final class RendShiftLeftOperation extends RendStdNumericOperation {

    public RendShiftLeftOperation(ShiftLeftOperation _s) {
        super(_s);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ExecutableCode _cont) {
        return new Argument(NumberStruct.calculateShiftLeft((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, getResultClass()));
    }

}
