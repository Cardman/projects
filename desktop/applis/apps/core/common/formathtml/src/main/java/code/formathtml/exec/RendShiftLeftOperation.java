package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.ShiftLeftOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.AliasNumber;

public final class RendShiftLeftOperation extends RendStdNumericOperation {

    public RendShiftLeftOperation(ShiftLeftOperation _s) {
        super(_s);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ContextEl _cont) {
        return new Argument(AliasNumber.calculateShiftLeft(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), getResultClass(), _cont.getStandards()));
    }

}
