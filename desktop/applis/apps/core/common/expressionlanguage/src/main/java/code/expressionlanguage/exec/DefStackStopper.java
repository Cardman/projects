package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;

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
    public boolean hasValueStd(StackCall _stack) {
        return false;
    }

    @Override
    public boolean isStopAtExcMethod() {
        return false;
    }

    @Override
    public int checkBpWithoutClearCount(StackCall _stack, AbstractPageEl _ip, int _old) {
        return 0;
    }
}
