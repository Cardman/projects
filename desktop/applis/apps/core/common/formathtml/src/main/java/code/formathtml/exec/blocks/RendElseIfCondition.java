package code.formathtml.exec.blocks;

import code.expressionlanguage.exec.ConditionReturn;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;
import code.util.CustList;

public final class RendElseIfCondition extends RendCondition implements RendWithEl {

    public RendElseIfCondition(int _offsetTrim, CustList<RendDynOperationNode> _op, int _offset) {
        super(_offsetTrim,_op,_offset);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendIfStack if_ = (RendIfStack) ip_.getRendLastStack();
        if_.setCurrentVisitedBlock(this);
        if (!if_.isEntered()) {
            ConditionReturn assert_ = evaluateCondition(_cont);
            if (assert_ == ConditionReturn.CALL_EX) {
                return;
            }
            if (assert_ == ConditionReturn.YES) {
                if_.setEntered(true);
                rw_.setRead(getFirstChild());
                return;
            }
        }
        if (if_.getLastBlock() == this) {
            processBlockAndRemove(_cont);
            return;
        }
        rw_.setRead(getNextSibling());
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
