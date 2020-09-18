package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.AddOperation;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NumberStruct;
import code.util.StringList;



public final class ExecAddOperation extends ExecStdNumericOperation {

    public ExecAddOperation(AddOperation _a) {
        super(_a);
    }

    static NumberStruct addOne(NumberStruct _arg, byte _cast) {
        return NumParsers.calculateIncr(_arg, 1, _cast);
    }

    static NumberStruct removeOne(NumberStruct _arg, byte _cast) {
        return NumParsers.calculateIncr(_arg, -1, _cast);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont) {
        return localSumDiff(_a, _op, _b, _cont);
    }


    private Argument localSumDiff(Argument _a, String _op, Argument _b,
                                  ContextEl _cont) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            return new Argument(NumParsers.calculateSum(NumParsers.convertToNumber(_a.getStruct()),
                    NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
        }
        return new Argument(NumParsers.calculateDiff(NumParsers.convertToNumber(_a.getStruct()),
                NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
    }

}
