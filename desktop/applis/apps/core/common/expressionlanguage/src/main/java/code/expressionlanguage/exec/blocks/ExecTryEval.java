package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.TryBlockStack;

public final class ExecTryEval extends ExecBracedBlock implements StackableBlock {

    private final String label;
    public ExecTryEval(String _label, int _offsetTrim) {
        super(_offsetTrim);
        label= _label;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
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
        ip_.setBlock(getFirstChild());
    }

}
