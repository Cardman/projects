package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;

public final class ExecDefaultCondition extends ExecBracedBlock implements
        StackableBlock {
    public ExecDefaultCondition(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.setGlobalOffset(getOffsetTrim());
        ip_.setOffset(0);
        ip_.setBlock(getFirstChild());
        ExecTemplates.setVisited(ip_,this);
    }
}
