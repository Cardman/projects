package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.stacks.IfBlockStack;
import code.util.CustList;

public final class UnclassedBracedBlock extends BracedStack implements WithEl {
    public UnclassedBracedBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.canCompleteNormally(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont);
            return;
        }
        IfBlockStack if_ = new IfBlockStack();
        if_.setLastBlock(this);
        if_.setBlock(this);
        if_.setCurrentVisitedBlock(this);
        ip_.addBlock(if_);
        if_.setEntered(true);
        rw_.setBlock(getFirstChild());
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {

    }

}
