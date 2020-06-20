package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.BitAndOperation;
import code.expressionlanguage.structs.NumberStruct;

public final class RendBitAndOperation extends RendStdNumericOperation {

    public RendBitAndOperation(BitAndOperation _b) {
        super(_b);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ContextEl _cont) {
        return new Argument(NumberStruct.calculateAnd(_a.getStruct(), _b.getStruct(), _cont, getResultClass()));
    }


}
