package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.TryBlockStack;
import code.expressionlanguage.analyze.files.OffsetsBlock;

public final class ExecTryEval extends ExecBracedBlock implements StackableBlock {

    private String label;
    public ExecTryEval(OffsetsBlock _offset, String _label) {
        super(_offset);
        label= _label;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ExecBlock n_ = getNextSibling();
        TryBlockStack tryStack_ = new TryBlockStack();
        tryStack_.setLabel(label);
        while (isNextTryParts(n_)) {
            tryStack_.setExecLastBlock((ExecBracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        tryStack_.setCurrentVisitedBlock(this);
        tryStack_.setExecBlock(this);
        ip_.addBlock(tryStack_);
        ip_.getReadWrite().setBlock(getFirstChild());
    }

}
