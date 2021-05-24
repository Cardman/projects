package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendIfCondition extends RendCondition implements RendWithEl {

    private final String label;

    public RendIfCondition(int _offsetTrim, CustList<RendDynOperationNode> _op, int _offset, String _label) {
        super(_offsetTrim,_op,_offset);
        label = _label;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        ConditionReturn assert_ = evaluateCondition(_cont, _stds, _ctx, _rendStack);
        if (assert_ == ConditionReturn.CALL_EX) {
            return;
        }
        RendIfStack if_ = new RendIfStack();
        if_.setLabel(label);
        if_.setLastBlock(this);
        RendBlock n_ = getNextSibling();
        while (isNextIfParts(n_)) {
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
            if (if_.getLastBlock() != this) {
                rw_.setRead(getNextSibling());
                ip_.setLastIf(if_);
            }
        }
    }

}
