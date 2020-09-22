package code.formathtml;

import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendTryBlockStack;

public abstract class RendAbstractCatchEval extends RendParentBlock implements RendEval {
    RendAbstractCatchEval(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public String getRealLabel() {
        RendBlock p_ = getPreviousSibling();
        while (!(p_ instanceof RendTryEval)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((RendTryEval)p_).getLabel();
    }

    @Override
    public final void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendTryBlockStack ts_ = (RendTryBlockStack) ip_.getRendLastStack();
        if (ts_.getLastBlock() == this) {
            processBlockAndRemove(_cont);
        } else {
            ts_.setCurrentVisitedBlock(this);
            rw_.setRead(getNextSibling());
        }
    }

}
