package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.files.OffsetsBlock;

public final class ExecDoBlock extends ExecBracedBlock implements ExecLoop {

    private String label;
    private int labelOffset;
    public ExecDoBlock(OffsetsBlock _offset, String _label, int _labelOffset) {
        super(_offset);
        label = _label;
        labelOffset = _labelOffset;
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(getNextSibling());
    }

    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,getNextSibling(),_cont);
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(label);
        l_.setExecBlock(this);
        l_.setCurrentVisitedBlock(this);
        ip_.addBlock(l_);
        rw_.setBlock(getFirstChild());
    }
}
