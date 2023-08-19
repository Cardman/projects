package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class DefStackStopper implements AbsStackStopper {
    @Override
    public int checkNext(ContextEl _context, StackCall _stackCall) {
        return 0;
    }

    @Override
    public StepDbgActionEnum firstStep() {
        return StepDbgActionEnum.RUN;
    }
    @Override
    public boolean firstEnter(AbstractPageEl _page) {
        return false;
    }

    @Override
    public boolean stopAt(StackCall _stack) {
        return false;
    }

    @Override
    public boolean isStopAtRef(ContextEl _context, StackCall _stackCall) {
        return false;
    }

    @Override
    public boolean isStopAt(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, StackCall _stackCall) {
        return false;
    }

    @Override
    public StopDbgEnum stopBreakPoint(ContextEl _context, StackCall _stackCall) {
        return StopDbgEnum.NONE;
    }

    @Override
    public boolean callsOrException(ContextEl _owner, StackCall _stackCall) {
        return false;
    }

    @Override
    public boolean isStopAtExcMethod() {
        return false;
    }

    @Override
    public ExpressionLanguageBp checkBpWithoutClear(StackCall _stack, int _index, AbstractPageEl _ip, CustList<ExecOperationNode> _list, ExecBlock _bl) {
        return new ExpressionLanguageBp(_ip.getCurrentEl(_index, _list, _bl),0);
    }
}
