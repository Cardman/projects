package code.formathtml.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.stacks.RendIfStack;

public final class RendElseCondition extends RendParentBlock implements RendWithEl, RendReducableOperations {

    public RendElseCondition(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendIfStack if_ = (RendIfStack) ip_.getRendLastStack();
        if_.setCurrentVisitedBlock(this);
        if (!if_.isEntered()) {
            if_.setEntered(true);
            ip_.getRendReadWrite().setRead(getFirstChild());
            return;
        }
        processBlockAndRemove(_cont);
    }

}
