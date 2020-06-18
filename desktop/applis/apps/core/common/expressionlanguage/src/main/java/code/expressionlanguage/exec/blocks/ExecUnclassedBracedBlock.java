package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.stacks.IfBlockStack;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.WithEl;
import code.util.CustList;

public final class ExecUnclassedBracedBlock extends ExecBracedBlock implements WithEl {
    public ExecUnclassedBracedBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {

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
        if_.setLabel("");
        if_.setExecLastBlock(this);
        if_.setExecBlock(this);
        if_.setCurrentVisitedBlock(this);
        ip_.addBlock(if_);
        if_.setEntered(true);
        rw_.setBlock(getFirstChild());
    }
}
