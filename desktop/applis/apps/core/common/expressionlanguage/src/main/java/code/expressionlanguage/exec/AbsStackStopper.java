package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;

public interface AbsStackStopper {
    StepDbgActionEnum firstStep();
    boolean firstEnter(AbstractPageEl _page);
    boolean stopAt(StackCall _stack);

    StopDbgEnum stopBreakPoint(ContextEl _context, StackCall _stackCall);

    boolean callsOrException(ContextEl _owner, StackCall _stackCall);

    int checkBpWithoutClearCount(StackCall _stack, AbstractPageEl _ip, int _old);

    boolean isStopAt(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, StackCall _stackCall);
    boolean isStopAtRef(ContextEl _context, StackCall _stackCall);

    boolean isStopAtExcMethod();

    int checkNext(ContextEl _context, StackCall _stackCall);
}
