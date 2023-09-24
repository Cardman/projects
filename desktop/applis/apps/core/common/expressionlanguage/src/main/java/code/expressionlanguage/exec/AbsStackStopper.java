package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.dbg.AbsLogDbg;

public interface AbsStackStopper {
    StepDbgActionEnum firstStep();
    boolean firstEnter(AbstractPageEl _page);
    boolean stopAt(StackCall _stack);

    StopDbgEnum stopBreakPoint(ContextEl _context, StackCall _stackCall);

    boolean callsOrException(ContextEl _owner, StackCall _stackCall);

    int checkBpWithoutClearCount(StackCall _stack, AbstractPageEl _ip, int _old);

    boolean isStopAtRef(ContextEl _context, StackCall _stackCall);

    boolean isStopAtExcMethod();

    int checkNext(ContextEl _context, StackCall _stackCall);

    boolean hasValueStd(StackCall _stack);
    AbsLogDbg getLogger();
}
