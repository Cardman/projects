package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;

public final class RendDoBlock extends RendParentBlock implements RendLoop {

    private final String label;

    public RendDoBlock(int _offsetTrim, String _label) {
        super(_offsetTrim);
        label = _label;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            if (c_.isFinished()) {
                RendBlock nextSibling_ = getNextSibling();
                if (nextSibling_ instanceof RendPossibleEmpty) {
                    nextSibling_ = nextSibling_.getNextSibling();
                }
                RendBlock next_ = nextSibling_;
                next_.processBlockAndRemove(_cont, _stds, _ctx, _stack, _rendStack);
                return;
            }
            rw_.setRead(getFirstChild());
            return;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLabel(label);
        l_.setBlock(this);
        l_.setLoop(this);
        l_.setCurrentVisitedBlock(this);
        ip_.addBlock(l_);
        rw_.setRead(getFirstChild());
    }

    @Override
    public void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _loopBlock, StackCall _stack, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock nextSibling_ = getNextSibling();
        if (nextSibling_ instanceof RendPossibleEmpty) {
            nextSibling_ = nextSibling_.getNextSibling();
        }
        rw_.setRead(nextSibling_);
    }

}
