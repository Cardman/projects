package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.ShiftRightOperation;
import code.expressionlanguage.structs.NumberStruct;

public final class ExecShiftRightOperation extends ExecNumericOperation {

    public ExecShiftRightOperation(ShiftRightOperation _b) {
        super(_b);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ExecutableCode _cont) {
        return new Argument(NumberStruct.calculateShiftRight((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, getResultClass()));
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b,
            Analyzable _an) {
        if (_a.isNull() || _b.isNull()) {
            return Argument.createVoid();
        }
        return new Argument(NumberStruct.calculateShiftRight((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _an, getResultClass()));
    }

}
