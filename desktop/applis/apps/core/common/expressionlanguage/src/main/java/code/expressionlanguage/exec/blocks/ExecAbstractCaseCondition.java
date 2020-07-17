package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.files.OffsetsBlock;

public abstract class ExecAbstractCaseCondition extends ExecBracedBlock implements
        StackableBlock {

    private int valueOffset;

    public ExecAbstractCaseCondition(OffsetsBlock _offset, int _valueOffset) {
        super(_offset);
        valueOffset = _valueOffset;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        rw_.setBlock(getFirstChild());
        ip_.getLastStack().setCurrentVisitedBlock(this);
    }

}
