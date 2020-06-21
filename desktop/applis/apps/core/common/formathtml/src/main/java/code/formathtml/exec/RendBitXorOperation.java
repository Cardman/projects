package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.BitXorOperation;
import code.expressionlanguage.stds.AliasNumber;

public final class RendBitXorOperation extends RendStdNumericOperation {

    public RendBitXorOperation(BitXorOperation _b) {
        super(_b);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ContextEl _cont) {
        return new Argument(AliasNumber.calculateXor(_a.getStruct(), _b.getStruct(), _cont, getResultClass()));
    }

}
