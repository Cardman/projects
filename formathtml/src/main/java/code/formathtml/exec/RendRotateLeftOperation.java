package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.RotateLeftOperation;
import code.expressionlanguage.structs.NumberStruct;

public final class RendRotateLeftOperation extends RendStdNumericOperation {

    public RendRotateLeftOperation(RotateLeftOperation _r) {
        super(_r);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ExecutableCode _cont) {
        return new Argument(NumberStruct.calculateRotateLeft((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, getResultClass()));
    }

}
