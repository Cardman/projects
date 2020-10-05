package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.ResultErrorStd;
import code.formathtml.Configuration;
import code.util.StringList;

public abstract class RendNumericOperation extends RendMethodOperation implements RendCalculableOperation {
    private int opOffset;

    public RendNumericOperation(ExecOperationContent _content, int _opOffset) {
        super(_content);
        opOffset = _opOffset;
    }

    static Argument calculateAffect(Argument _left, Configuration _conf, Argument _right, String _op, boolean _catString, StringList _cls, byte _cast, ContextEl _context) {
        ResultErrorStd res_= new ResultErrorStd();
        ExecNumericOperation.calculateOperator(_conf.getPageEl(), _context, res_, _op, _catString, _left.getStruct(), _right.getStruct(), _cls, _cast);
        return new Argument(res_.getResult());
    }

    public int getOpOffset() {
        return opOffset;
    }
}
