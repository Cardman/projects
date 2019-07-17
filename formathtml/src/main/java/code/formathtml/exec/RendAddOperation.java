package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.AddOperation;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringStruct;
import code.util.StringList;



public final class RendAddOperation extends RendStdNumericOperation {

    private boolean catString;

    public RendAddOperation(AddOperation _a) {
        super(_a);
        catString = _a.isCatString();
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ExecutableCode _cont) {
        return localSumDiff(_a, _op, _b, _cont);
    }


    private Argument localSumDiff(Argument _a, String _op, Argument _b,
            ExecutableCode _cont) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            if (catString) {
                StringBuilder str_ = new StringBuilder();
                str_.append(((DisplayableStruct)_a.getStruct()).getDisplayedString(_cont).getInstance());
                str_.append(((DisplayableStruct)_b.getStruct()).getDisplayedString(_cont).getInstance());
                return new Argument(new StringStruct(str_.toString()));
            }
            return new Argument(NumberStruct.calculateSum((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, getResultClass()));
        }
        return new Argument(NumberStruct.calculateDiff((NumberStruct) _a.getStruct(),(NumberStruct) _b.getStruct(), _cont, getResultClass()));
    }

}
