package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendTryBlockStack;
import code.formathtml.util.BeanLgNames;

public abstract class RendAbstractCatchEval extends RendParentBlock implements RendEval {
    RendAbstractCatchEval(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public final void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendTryBlockStack ts_ = (RendTryBlockStack) ip_.getRendLastStack();
        if (ts_.getLastBlock() == this) {
            processBlockAndRemove(_cont, _stds, _ctx);
        } else {
            ts_.setCurrentVisitedBlock(this);
            rw_.setRead(getNextSibling());
        }
    }

}
