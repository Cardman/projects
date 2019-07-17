package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.NumericOperation;
import code.expressionlanguage.opers.SymbolOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.NumberStruct;
import code.util.CustList;

public abstract class RendNumericOperation extends RendMethodOperation implements RendCalculableOperation {
    private int opOffset;

    public RendNumericOperation(SymbolOperation _n) {
        super(_n);
        opOffset = _n.getOpOffset();
    }

    static Argument calculateAffect(Argument _left,ExecutableCode _conf, Argument _right, String _op, boolean _catString, ClassArgumentMatching _arg) {
        ResultErrorStd res_= new ResultErrorStd();
        NumberStruct.calculateOperator(_conf, res_, _arg, _op, _catString, _left.getStruct(), _right.getStruct());
        return new Argument(res_.getResult());
    }

    public int getOpOffset() {
        return opOffset;
    }
}
