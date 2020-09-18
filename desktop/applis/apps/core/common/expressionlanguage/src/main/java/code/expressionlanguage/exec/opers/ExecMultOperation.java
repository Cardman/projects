package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.MultOperation;
import code.expressionlanguage.common.NumParsers;
import code.util.StringList;


public final class ExecMultOperation extends ExecStdNumericOperation {

    public ExecMultOperation(MultOperation _m) {
        super(_m);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont) {
        if (StringList.quickEq(_op.trim(), MULT)) {
            return new Argument(NumParsers.calculateMult(NumParsers.convertToNumber(_a.getStruct()),
                    NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
        }
        if (StringList.quickEq(_op.trim(), DIV)) {
            return calculateDivEx(_a, _cont, _b, getResultClass().getUnwrapObjectNb());
        }
        return calculateModEx(_a, _cont, _b, getResultClass().getUnwrapObjectNb());
    }

}
