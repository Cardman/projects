package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.inherits.ExecTemplates;

public final class ExecDefaultCondition extends ExecBracedBlock implements
        StackableBlock {
    public ExecDefaultCondition(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ip_.setGlobalOffset(getOffsetTrim());
        ip_.setOffset(0);
        ip_.setBlock(getFirstChild());
        ExecTemplates.setVisited(ip_,this);
    }
}
