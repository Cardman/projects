package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.BitShiftRightOperation;
import code.expressionlanguage.structs.NumberStruct;

public final class ExecBitShiftRightOperation extends ExecNumericOperation {

    public ExecBitShiftRightOperation(BitShiftRightOperation _b) {
        super(_b);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ExecutableCode _cont) {
        return new Argument(NumberStruct.calculateBitShiftRight((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, getResultClass()));
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b,
            Analyzable _an) {
        if (_a.isNull() || _b.isNull()) {
            return Argument.createVoid();
        }
        return new Argument(NumberStruct.calculateBitShiftRight((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _an, getResultClass()));
    }

}
