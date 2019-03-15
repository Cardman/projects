package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.AddOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.util.StringList;



public final class ExecAddOperation extends ExecStdNumericOperation {

    public ExecAddOperation(AddOperation _a) {
        super(_a);
    }

    static NumberStruct addOne(NumberStruct _arg, ExecutableCode _cont, ClassArgumentMatching _cl) {
        return NumberStruct.calculateSum(_arg, new IntStruct(1), _cont, _cl);
    }

    static NumberStruct removeOne(NumberStruct _arg, ExecutableCode _cont, ClassArgumentMatching _cl) {
        return NumberStruct.calculateDiff(_arg, new IntStruct(1), _cont, _cl);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ExecutableCode _cont) {
        return localSumDiff(_a, _op, _b, _cont);
    }


    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b, Analyzable _cont) {
        return localSumDiff(_a, _op, _b, _cont.getContextEl());
    }

    private Argument localSumDiff(Argument _a, String _op, Argument _b,
            ExecutableCode _cont) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            return new Argument(NumberStruct.calculateSum((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, getResultClass()));
        }
        return new Argument(NumberStruct.calculateDiff((NumberStruct) _a.getStruct(),(NumberStruct) _b.getStruct(), _cont, getResultClass()));
    }

}
