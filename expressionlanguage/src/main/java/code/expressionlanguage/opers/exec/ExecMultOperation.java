package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.MultOperation;
import code.expressionlanguage.structs.NumberStruct;
import code.util.StringList;


public final class ExecMultOperation extends ExecStdNumericOperation {

    public ExecMultOperation(MultOperation _m) {
        super(_m);
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b,
            Analyzable _an) {
        if (StringList.quickEq(_op.trim(), MULT)) {
            return new Argument(NumberStruct.calculateMult((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _an, getResultClass()));
        }
        if (StringList.quickEq(_op.trim(), DIV)) {
            return new Argument(NumberStruct.calculateDiv((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _an, getResultClass()));
        }
        return new Argument(NumberStruct.calculateMod((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _an, getResultClass()));
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ExecutableCode _cont) {
        if (StringList.quickEq(_op.trim(), MULT)) {
            return new Argument(NumberStruct.calculateMult((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, getResultClass()));
        }
        if (StringList.quickEq(_op.trim(), DIV)) {
            return calculateDivEx(_a, _cont, _b, getResultClass());
        }
        return calculateModEx(_a, _cont, _b, getResultClass());
    }

}
