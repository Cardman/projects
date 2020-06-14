package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.AddOperation;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.NumberStruct;
import code.util.StringList;



public final class RendAddOperation extends RendStdNumericOperation {

    private boolean catString;

    public RendAddOperation(AddOperation _a) {
        super(_a);
        catString = _a.isCatString();
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont) {
        return localSumDiff(_a, _op, _b, _cont);
    }


    private Argument localSumDiff(Argument _a, String _op, Argument _b,
            ContextEl _cont) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            if (catString) {
                return ExecCatOperation.localSumDiff(_a, _b, _cont);
            }
            return new Argument(NumberStruct.calculateSum(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                    ClassArgumentMatching.convertToNumber(_b.getStruct()), _cont, getResultClass()));
        }
        return new Argument(NumberStruct.calculateDiff(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), _cont, getResultClass()));
    }

}
