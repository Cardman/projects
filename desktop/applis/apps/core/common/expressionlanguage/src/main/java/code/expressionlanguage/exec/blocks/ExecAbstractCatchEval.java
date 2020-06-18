package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.stacks.TryBlockStack;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.StackableBlock;
import code.util.CustList;

public abstract class ExecAbstractCatchEval extends ExecBracedBlock implements StackableBlock {

    ExecAbstractCatchEval(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        String tag_;
        if (_cont.getCoverage().getCatches(this)) {
            tag_ = "<span class=\"f\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordCatch().length()));
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        TryBlockStack ts_ = (TryBlockStack) ip_.getLastStack();
        if (ts_.getLastBlock() == this) {
            processBlockAndRemove(_cont);
        } else {
            ts_.setCurrentVisitedBlock(this);
            rw_.setBlock(getNextSibling());
        }
    }
}
