package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.SymbolOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.formathtml.Configuration;

public abstract class RendNumericOperation extends RendMethodOperation implements RendCalculableOperation {
    private int opOffset;

    public RendNumericOperation(SymbolOperation _n, OperationNode _op) {
        super(_op);
        opOffset = _n.getOpOffset();
    }

    static Argument calculateAffect(Argument _left, Configuration _conf, Argument _right, String _op, boolean _catString, ClassArgumentMatching _arg) {
        ResultErrorStd res_= new ResultErrorStd();
        if (_right == null) {
            NumberStruct.calculateOperator(_conf.getPageEl(),_conf.getContext(), res_, _arg, _op, _catString, _left.getStruct(), NullStruct.NULL_VALUE);
        } else {
            NumberStruct.calculateOperator(_conf.getPageEl(),_conf.getContext(), res_, _arg, _op, _catString, _left.getStruct(), _right.getStruct());
        }
        return new Argument(res_.getResult());
    }

    public int getOpOffset() {
        return opOffset;
    }
}
