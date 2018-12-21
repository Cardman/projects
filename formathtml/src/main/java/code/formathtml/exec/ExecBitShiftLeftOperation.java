package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.BitShiftLeftOperation;
import code.expressionlanguage.structs.NumberStruct;

public final class ExecBitShiftLeftOperation extends ExecNumericOperation {

    public ExecBitShiftLeftOperation(BitShiftLeftOperation _b) {
        super(_b);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ExecutableCode _cont) {
        return new Argument(NumberStruct.calculateBitShiftLeft((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, getResultClass()));
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b,
            Analyzable _an) {
        if (_a.isNull() || _b.isNull()) {
            return Argument.createVoid();
        }
        return new Argument(NumberStruct.calculateBitShiftLeft((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _an, getResultClass()));
    }

}
