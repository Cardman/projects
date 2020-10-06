package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;

public abstract class RendAbstractCatchEval extends RendParentBlock implements RendEval {
    RendAbstractCatchEval(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public final void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock n_ = getNextSibling();
        if (n_ instanceof RendPossibleEmpty) {
            n_ = n_.getNextSibling();
        }
        if (isStrictNextTryParts(n_)) {
            rw_.setRead(n_);
        } else {
            processBlockAndRemove(_cont, _stds, _ctx);
        }
    }

}
