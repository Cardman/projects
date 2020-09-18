package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.SymbolOperation;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.stds.ResultErrorStd;
import code.formathtml.Configuration;
import code.util.StringList;

public abstract class RendNumericOperation extends RendMethodOperation implements RendCalculableOperation {
    private int opOffset;

    public RendNumericOperation(SymbolOperation _n, OperationNode _op) {
        super(_op);
        opOffset = _n.getOpOffset();
    }

    static Argument calculateAffect(Argument _left, Configuration _conf, Argument _right, String _op, boolean _catString, StringList _cls, byte _cast) {
        ResultErrorStd res_= new ResultErrorStd();
        ExecNumericOperation.calculateOperator(_conf.getPageEl(),_conf.getContext(), res_, _op, _catString, _left.getStruct(), _right.getStruct(), _cls, _cast);
        return new Argument(res_.getResult());
    }

    public int getOpOffset() {
        return opOffset;
    }
}
