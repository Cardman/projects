package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.stacks.RendTryBlockStack;
import code.formathtml.util.BeanLgNames;

public final class RendTryEval extends RendParentBlock implements RendWithEl,RendEval {

    private final String label;

    public RendTryEval(String _label) {
        label = _label;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendBlock n_ = getNextSibling();
        RendTryBlockStack tryStack_ = new RendTryBlockStack();
        tryStack_.setLabel(label);
        while (isNextTryParts(n_)) {
            if (n_ instanceof RendParentBlock) {
                tryStack_.setLastBlock((RendParentBlock) n_);
            }
            n_ = n_.getNextSibling();
        }
        tryStack_.setCurrentVisitedBlock(this);
        ip_.addBlock(tryStack_);
        ip_.getRendReadWrite().setRead(getFirstChild());
    }

}
