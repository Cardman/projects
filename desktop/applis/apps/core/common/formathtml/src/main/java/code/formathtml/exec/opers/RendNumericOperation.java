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
import code.util.core.StringUtil;

public abstract class RendNumericOperation extends RendMethodOperation implements RendCalculableOperation {
    private final int opOffset;

    public RendNumericOperation(ExecOperationContent _content, int _opOffset) {
        super(_content);
        opOffset = _opOffset;
    }

    static Argument calculateAffect(Argument _left, Argument _right, String _op, StringList _cls, byte _cast, ContextEl _context, RendStackCall _stackCall) {
        ResultErrorStd res_= new ResultErrorStd();
        String op_ = _op.substring(0, _op.length() - 1);
        if (StringUtil.quickEq(op_, "??") || StringUtil.quickEq(op_, "???")) {
            Struct first_ = _left.getStruct();
            if (first_ != NullStruct.NULL_VALUE) {
                res_.setResult(ExecClassArgumentMatching.convert(first_,_context, _cls));
            } else {
                res_.setResult(ExecClassArgumentMatching.convert(_right.getStruct(),_context, _cls));
            }
            return new Argument(res_.getResult());
        }
        ExecNumericOperation.calculateOperator(_context, res_, _op, _left.getStruct(), _right.getStruct(), _cast, _stackCall.getStackCall());
        return new Argument(res_.getResult());
    }

    public int getOpOffset() {
        return opOffset;
    }
}
