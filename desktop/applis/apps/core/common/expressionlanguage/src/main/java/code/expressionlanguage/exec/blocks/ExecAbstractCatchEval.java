package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public abstract class ExecAbstractCatchEval extends ExecBracedBlock implements StackableBlock {

    ExecAbstractCatchEval(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        if (isNextTryParts(getNextSibling())) {
            ip_.setBlock(getNextSibling());
        } else {
            processBlockAndRemove(_cont, _stack);
        }
    }
}
