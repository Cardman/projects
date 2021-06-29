package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;

public final class RendDoBlock extends RendParentBlock implements RendWithEl {

    private final String label;

    public RendDoBlock(String _label) {
        label = _label;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            RendBlock nextSibling_ = getNextSibling();
            if (nextSibling_ instanceof RendPossibleEmpty) {
                nextSibling_ = nextSibling_.getNextSibling();
            }
            RendBlock next_ = nextSibling_;
            processVisitedLoop(_cont,_stds,c_,next_,_ctx,_rendStack);
//            if (c_.getContent().isFinished()) {
//                next_.processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
//                return;
//            }
//            rw_.setRead(getFirstChild());
            return;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLabel(label);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        ip_.addBlock(l_);
        rw_.setRead(getFirstChild());
    }

    public void processLastElementLoop(RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock nextSibling_ = getNextSibling();
        if (nextSibling_ instanceof RendPossibleEmpty) {
            nextSibling_ = nextSibling_.getNextSibling();
        }
        rw_.setRead(nextSibling_);
    }

}
