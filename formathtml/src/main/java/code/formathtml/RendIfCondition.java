package code.formathtml;

import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendRemovableVars;

public final class RendIfCondition extends RendCondition implements RendBreakableBlock {

    private String label;
    private int labelOffset;
    RendIfCondition(OffsetStringInfo _condition, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_condition, _offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.hasBlock()) {
            RendRemovableVars bl_ = ip_.getRendLastStack();
            if (bl_.getBlock() == this) {
                ip_.removeRendLastBlock();
                processBlock(_cont);
                return;
            }
        }
        Boolean assert_ = evaluateCondition(_cont);
        if (assert_ == null) {
            return;
        }
        RendIfStack if_ = new RendIfStack();
        if_.setLastBlock(this);
        RendBlock n_ = getNextSibling();
        while (n_ instanceof RendElseIfCondition || n_ instanceof RendElseCondition || n_ instanceof RendPossibleEmpty) {
            if (n_ instanceof RendParentBlock) {
                if_.setLastBlock((RendParentBlock) n_);
            }
            n_ = n_.getNextSibling();
        }
        if_.setBlock(this);
        if_.setCurentVisitedBlock(this);
        if (assert_) {
            ip_.addBlock(if_);
            if_.setEntered(true);
            rw_.setRead(getFirstChild());
        } else {
            ip_.addBlock(if_);
            if (if_.getLastBlock() == this) {
                return;
            }
            rw_.setRead(getNextSibling());
        }
    }

    @Override
    public void exitStack(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendIfStack if_ = (RendIfStack) ip_.getRendLastStack();
        if (if_.getLastBlock() == this) {
            rw_.setRead(this);
        } else {
            rw_.setRead(getNextSibling());
        }
    }
}
