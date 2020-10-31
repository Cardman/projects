package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.stacks.IfBlockStack;

public final class ExecUnclassedBracedBlock extends ExecBracedBlock implements WithEl {
    public ExecUnclassedBracedBlock(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
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
        ip_.setBlock(getFirstChild());
    }
}
