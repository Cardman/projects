package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public interface AbsStackStopper {
    StepDbgActionEnum firstStep();
    boolean firstEnter(AbstractPageEl _page);
    boolean stopAt(StackCall _stack);
    boolean stopAt(ContextEl _context, StackCall _stack);
    StopDbgEnum stopBreakPoint(ContextEl _context, StackCall _stackCall);

    boolean callsOrException(ContextEl _owner, StackCall _stackCall);
    ExpressionLanguageBp checkBpWithoutClear(StackCall _stack, int _index, AbstractPageEl _ip, CustList<ExecOperationNode> _list, ExecBlock _bl);

    boolean isStopAt(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, StackCall _stackCall);
    boolean isStopAtRef(ContextEl _context, StackCall _stackCall);

    boolean isStopAtExcMethod();

    boolean hasToCheckExit(StackCall _stack, AbstractPageEl _p);
}
