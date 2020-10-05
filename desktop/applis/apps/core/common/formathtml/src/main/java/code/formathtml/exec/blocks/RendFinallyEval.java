package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.stacks.RendAbruptCallingFinally;
import code.formathtml.stacks.RendExceptionCallingFinally;
import code.formathtml.stacks.RendTryBlockStack;
import code.formathtml.util.BeanLgNames;

public final class RendFinallyEval extends RendParentBlock implements RendEval {
    public RendFinallyEval(int _offsetTrim) {
        super(_offsetTrim);
    }


    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage ip_ = _cont.getLastPage();
        RendTryBlockStack ts_ = (RendTryBlockStack) ip_.getRendLastStack();
        ts_.setCurrentVisitedBlock(this);
        if (ts_.isVisitedFinally()) {
            processBlockAndRemove(_cont, _stds, _ctx);
            return;
        }
        ts_.setVisitedFinally(true);
        ip_.getRendReadWrite().setRead(getFirstChild());
    }

    @Override
    public void exitStack(Configuration _context, BeanLgNames _advStandards, ContextEl _ctx) {
        ImportingPage ip_ = _context.getLastPage();
        RendTryBlockStack tryStack_ = (RendTryBlockStack) ip_.getRendLastStack();
        RendAbruptCallingFinally call_ = tryStack_.getCalling();
        if (call_ != null) {
            RendCallingFinally callingFinally_ = call_.getCallingFinally();
            if (call_ instanceof RendExceptionCallingFinally) {
                _ctx.setException(((RendExceptionCallingFinally)call_).getException());
            }
            callingFinally_.removeBlockFinally(_context, _advStandards, _ctx);
        }
    }
}
