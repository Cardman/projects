package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.analyze.files.OffsetsBlock;

public final class ExecDefaultCondition extends ExecBracedBlock implements
        StackableBlock {
    public ExecDefaultCondition(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        ip_.setGlobalOffset(getOffset().getOffsetTrim());
        ip_.setOffset(0);
        rw_.setBlock(getFirstChild());
        ExecTemplates.setVisited(ip_,this);
    }
}
