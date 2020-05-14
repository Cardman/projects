package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.RotateRightOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.NumberStruct;

public final class RendRotateRightOperation extends RendStdNumericOperation {

    public RendRotateRightOperation(RotateRightOperation _r) {
        super(_r);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ExecutableCode _cont) {
        return new Argument(NumberStruct.calculateRotateRight(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), _cont, getResultClass()));
    }


}
