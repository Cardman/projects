package code.formathtml;

import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendTryBlockStack;

public final class RendTryEval extends RendParentBlock implements RendBreakableBlock,RendEval {

    private String label;

    public RendTryEval(String _label, int _offsetTrim) {
        super(_offsetTrim);
        label = _label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendBlock n_ = getNextSibling();
        RendTryBlockStack tryStack_ = new RendTryBlockStack();
        while (n_ instanceof RendAbstractCatchEval || n_ instanceof RendFinallyEval || n_ instanceof RendPossibleEmpty) {
            if (n_ instanceof RendParentBlock) {
                tryStack_.setLastBlock((RendParentBlock) n_);
            }
            n_ = n_.getNextSibling();
        }
        tryStack_.setCurrentVisitedBlock(this);
        tryStack_.setBlock(this);
        ip_.addBlock(tryStack_);
        ip_.getRendReadWrite().setRead(getFirstChild());
    }

    @Override
    public void exitStack(Configuration _context) {
        ImportingPage ip_ = _context.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(getNextSibling());
    }
}
