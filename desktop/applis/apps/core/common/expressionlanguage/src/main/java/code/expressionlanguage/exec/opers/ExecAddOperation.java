package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.AddOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.AliasNumber;
import code.expressionlanguage.structs.NumberStruct;
import code.util.StringList;



public final class ExecAddOperation extends ExecStdNumericOperation {

    public ExecAddOperation(AddOperation _a) {
        super(_a);
    }

    static NumberStruct addOne(NumberStruct _arg, ContextEl _cont, ClassArgumentMatching _cl) {
        return AliasNumber.calculateIncr(_arg, 1, _cont, _cl);
    }

    static NumberStruct removeOne(NumberStruct _arg, ContextEl _cont, ClassArgumentMatching _cl) {
        return AliasNumber.calculateIncr(_arg, -1, _cont, _cl);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont) {
        return localSumDiff(_a, _op, _b, _cont);
    }


    private Argument localSumDiff(Argument _a, String _op, Argument _b,
                                  ContextEl _cont) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            return new Argument(AliasNumber.calculateSum(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                    ClassArgumentMatching.convertToNumber(_b.getStruct()), _cont, getResultClass()));
        }
        return new Argument(AliasNumber.calculateDiff(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), _cont, getResultClass()));
    }

}
