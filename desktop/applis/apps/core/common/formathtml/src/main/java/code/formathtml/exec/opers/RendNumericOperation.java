package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.StringList;

public abstract class RendNumericOperation extends RendMethodOperation implements RendCalculableOperation {
    private final int opOffset;

    protected RendNumericOperation(ExecOperationContent _content, int _opOffset) {
        super(_content);
        opOffset = _opOffset;
    }

    static Argument calculateAffect(Argument _left, Argument _right, String _op, byte _cast, ContextEl _context, RendStackCall _stackCall) {
        ResultErrorStd res_= new ResultErrorStd();
        ExecNumericOperation.calculateOperator(_context, res_, _op, _left.getStruct(), _right.getStruct(), _cast, _stackCall.getStackCall());
        return new Argument(res_.getResult());
    }

    static Argument calculateAffect(Argument _left, Argument _right, StringList _cls, ContextEl _conf) {
        Struct first_ = _left.getStruct();
        Argument res_;
        if (first_ != NullStruct.NULL_VALUE) {
            res_=new Argument(ExecClassArgumentMatching.convert(first_, _conf, _cls));
        } else {
            res_=new Argument(ExecClassArgumentMatching.convert(_right.getStruct(), _conf, _cls));
        }
        return res_;
    }

    public int getOpOffset() {
        return opOffset;
    }
}
