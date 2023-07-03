package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.AbsStackStopper;
import code.expressionlanguage.exec.ExpressionLanguageBp;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StepDbgActionEnum;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecHelperBlocks;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class DbgStackStopper implements AbsStackStopper {
    @Override
    public StepDbgActionEnum firstStep() {
        return StepDbgActionEnum.DEBUG;
    }

    @Override
    public boolean firstEnter(AbstractPageEl _page) {
        return _page.sizeEl() == 0;
    }
    @Override
    public boolean stopAt(AbstractPageEl _page, int _size) {
        return _size < _page.sizeEl();
    }

    @Override
    public boolean stopAt(ContextEl _context, StackCall _stack, int _size) {
        return _size < _stack.getLastPage().sizeEl() || _context.callsOrException(_stack);
    }

    @Override
    public boolean stopBreakPoint(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        return p_.stopBreakPoint(_context,_stackCall);
    }

    @Override
    public boolean isCheckingException(StackCall _stackCall) {
        return _stackCall.isCheckingException();
    }

    @Override
    public boolean hasFoundException(StackCall _stackCall) {
        return _stackCall.trueException() != null;
    }

    @Override
    public ExpressionLanguageBp checkBpWithoutClear(StackCall _stack, int _index, AbstractPageEl _ip, CustList<ExecOperationNode> _list, ExecBlock _bl) {
        return ExecHelperBlocks.checkBpWithoutClear(_stack, _index, _ip, _list, _bl);
    }
}
