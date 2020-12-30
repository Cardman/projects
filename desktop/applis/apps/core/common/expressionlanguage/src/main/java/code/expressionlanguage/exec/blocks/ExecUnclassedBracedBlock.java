package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.IfBlockStack;

public final class ExecUnclassedBracedBlock extends ExecBracedBlock implements WithEl {
    public ExecUnclassedBracedBlock(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont, _stack);
            return;
        }
        IfBlockStack if_ = new IfBlockStack();
        if_.setLabel("");
        if_.setExecLastBlock(this);
        if_.setExecBlock(this);
        if_.setCurrentVisitedBlock(this);
        ip_.addBlock(if_);
        if_.setEntered(true);
        ip_.setBlock(getFirstChild());
    }
}
