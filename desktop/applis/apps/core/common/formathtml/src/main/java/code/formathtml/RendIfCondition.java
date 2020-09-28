package code.formathtml;

import code.expressionlanguage.exec.ConditionReturn;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;
import code.util.CustList;

public final class RendIfCondition extends RendCondition implements RendBreakableBlock {

    private String label;

    public RendIfCondition(int _offsetTrim, CustList<RendDynOperationNode> _op, int _offset, String _label) {
        super(_offsetTrim,_op,_offset);
        label = _label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont);
            return;
        }
        ConditionReturn assert_ = evaluateCondition(_cont);
        if (assert_ == ConditionReturn.CALL_EX) {
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
        if_.setCurrentVisitedBlock(this);
        if (assert_ == ConditionReturn.YES) {
            ip_.addBlock(if_);
            if_.setEntered(true);
            rw_.setRead(getFirstChild());
        } else {
            ip_.addBlock(if_);
            exitStack(_cont);
        }
    }

    @Override
    public void exitStack(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendIfStack if_ = (RendIfStack) ip_.getRendLastStack();
        if (if_.getLastBlock() != this) {
            rw_.setRead(getNextSibling());
        }
    }
}
