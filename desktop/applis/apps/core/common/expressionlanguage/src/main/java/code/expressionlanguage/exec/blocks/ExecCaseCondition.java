package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.CustList;

public final class ExecCaseCondition extends ExecSwitchPartBlock {

    private final String value;
    private CustList<ExecOperationNode> opValue;

    private int valueOffset;

    public ExecCaseCondition(OffsetsBlock _offset, String _value, int _valueOffset, CustList<ExecOperationNode> _opValue) {
        super(_offset);
        value = _value;
        valueOffset = _valueOffset;
        opValue = _opValue;
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

    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

    public String getValue() {
        return value;
    }
}
