package code.formathtml;

import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;

public final class RendDoBlock extends RendParentBlock implements RendLoop {

    private String label;

    public RendDoBlock(int _offsetTrim, String _label) {
        super(_offsetTrim);
        label = _label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return getLabel();
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            if (c_.isFinished()) {
                RendBlock nextSibling_ = getNextSibling();
                if (nextSibling_ instanceof RendPossibleEmpty) {
                    nextSibling_ = nextSibling_.getNextSibling();
                }
                RendBlock next_ = nextSibling_;
                next_.processBlockAndRemove(_cont);
                return;
            }
            rw_.setRead(getFirstChild());
            return;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        ip_.addBlock(l_);
        rw_.setRead(getFirstChild());
    }

    @Override
    public void exitStack(Configuration _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void processLastElementLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock nextSibling_ = getNextSibling();
        if (nextSibling_ instanceof RendPossibleEmpty) {
            nextSibling_ = nextSibling_.getNextSibling();
        }
        rw_.setRead(nextSibling_);
    }

}
