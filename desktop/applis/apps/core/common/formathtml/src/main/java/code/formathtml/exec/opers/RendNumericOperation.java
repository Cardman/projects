package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.StringList;

public abstract class RendNumericOperation extends RendMethodOperation implements RendCalculableOperation {
    private final int opOffset;

    protected RendNumericOperation(ExecOperationContent _content, int _opOffset) {
        super(_content);
        opOffset = _opOffset;
    }

    static Argument calculateAffect(Argument _left, Argument _right, String _op, byte _cast, ContextEl _context, RendStackCall _stackCall) {
        return new Argument(ExecNumericOperation.calculateOperator(_context, _op, _left.getStruct(), _right.getStruct(), _cast, _stackCall.getStackCall()));
    }

    static Argument calculateAffect(Argument _left, Argument _right, StringList _cls, ContextEl _conf,RendStackCall _stack) {
        return ExecNumericOperation.calculateAffect(_left,_conf,_right,_cls,_stack);
    }

    public int getOpOffset() {
        return opOffset;
    }
}
