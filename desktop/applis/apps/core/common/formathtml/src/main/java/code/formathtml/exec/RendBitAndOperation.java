package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.BitAndOperation;
import code.expressionlanguage.stds.AliasNumber;

public final class RendBitAndOperation extends RendStdNumericOperation {

    public RendBitAndOperation(BitAndOperation _b) {
        super(_b);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ContextEl _cont) {
        return new Argument(AliasNumber.calculateAnd(_a.getStruct(), _b.getStruct(), getResultClass(), _cont.getStandards()));
    }


}
