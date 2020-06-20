package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.stacks.TryBlockStack;
import code.expressionlanguage.files.OffsetsBlock;

public final class ExecTryEval extends ExecBracedBlock implements StackableBlock {

    private String label;
    private int labelOffset;
    public ExecTryEval(OffsetsBlock _offset, String _label, int _labelOffset) {
        super(_offset);
        label= _label;
        labelOffset = _labelOffset;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ExecBlock n_ = getNextSibling();
        TryBlockStack tryStack_ = new TryBlockStack();
        tryStack_.setLabel(label);
        while (n_ instanceof ExecAbstractCatchEval || n_ instanceof ExecFinallyEval) {
            tryStack_.setExecLastBlock((ExecBracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        tryStack_.setCurrentVisitedBlock(this);
        tryStack_.setExecBlock(this);
        ip_.addBlock(tryStack_);
        ip_.getReadWrite().setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(getNextSibling());
    }
}
