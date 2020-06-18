package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.util.CustList;

public final class ExecDefaultCondition extends ExecSwitchPartBlock {
    public ExecDefaultCondition(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        ExecBracedBlock parent_ = getParent();
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverSwitchs(parent_,this);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordDefault().length()));
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        ip_.setGlobalOffset(getOffset().getOffsetTrim());
        ip_.setOffset(0);
        rw_.setBlock(getFirstChild());
        ip_.getLastStack().setCurrentVisitedBlock(this);
    }
}
