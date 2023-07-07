package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class DefStackStopper implements AbsStackStopper {
    @Override
    public StepDbgActionEnum firstStep() {
        return StepDbgActionEnum.RUN;
    }
    @Override
    public boolean firstEnter(AbstractPageEl _page) {
        return false;
    }

    @Override
    public boolean stopAt(AbstractPageEl _page, StackCall _stack, int _size) {
        return false;
    }

    @Override
    public boolean stopAt(ContextEl _context, StackCall _stack, int _size) {
        return _context.callsOrException(_stack);
    }

    @Override
    public boolean isStopAtRefGetField(FieldMetaInfo _meta, Struct _instance, ContextEl _context, StackCall _stackCall) {
        return false;
    }

    @Override
    public boolean isStopAtRefGetVar(ArgumentListCall _meta, ContextEl _context, StackCall _stackCall) {
        return false;
    }

    @Override
    public boolean isStopAtRefSetField(FieldMetaInfo _meta, Struct _instance, Struct _right, ContextEl _context, StackCall _stackCall) {
        return false;
    }

    @Override
    public boolean isStopAtRefSetVar(ArgumentListCall _meta, ContextEl _context, StackCall _stackCall) {
        return false;
    }

    @Override
    public boolean isStopAt(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, StackCall _stackCall) {
        return false;
    }

    @Override
    public boolean isChecking(ExpressionLanguage _o, ContextEl _context, StackCall _stackCall) {
        return _context.callsOrException(_stackCall);
    }

    @Override
    public boolean stopBreakPoint(ContextEl _context, StackCall _stackCall) {
        return false;
    }

    @Override
    public boolean isCheckingException(StackCall _stackCall) {
        return false;
    }

    @Override
    public boolean hasFoundException(StackCall _stackCall) {
        return false;
    }

    @Override
    public ExpressionLanguageBp checkBpWithoutClear(StackCall _stack, int _index, AbstractPageEl _ip, CustList<ExecOperationNode> _list, ExecBlock _bl) {
        return new ExpressionLanguageBp(_ip.getCurrentEl(_stack,_index, _list, _bl),0);
    }
}
