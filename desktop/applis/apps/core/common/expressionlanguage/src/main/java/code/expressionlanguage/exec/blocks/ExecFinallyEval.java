package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.AbruptCallingFinally;
import code.expressionlanguage.exec.stacks.ExceptionCallingFinally;
import code.expressionlanguage.exec.stacks.TryBlockStack;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.methods.CallingFinally;
import code.expressionlanguage.methods.StackableBlock;

public final class ExecFinallyEval extends ExecBracedBlock implements StackableBlock {

    public ExecFinallyEval(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        TryBlockStack ts_ = (TryBlockStack) ip_.getLastStack();
        ts_.setCurrentVisitedBlock(this);
        if (ts_.isVisitedFinally()) {
            processBlockAndRemove(_cont);
            return;
        }
        ts_.setVisitedFinally(true);
        ip_.getReadWrite().setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        TryBlockStack tryStack_ = (TryBlockStack) ip_.getLastStack();
        AbruptCallingFinally call_ = tryStack_.getCalling();
        if (call_ != null) {
            CallingFinally callingFinally_ = call_.getCallingFinally();
            if (call_ instanceof ExceptionCallingFinally) {
                _context.setException(((ExceptionCallingFinally)call_).getException());
            }
            callingFinally_.removeBlockFinally(_context);
        }
    }
}
