package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;

public abstract class ExecAbstractCaseCondition extends ExecBracedBlock implements
        StackableBlock {

    private final int valueOffset;

    protected ExecAbstractCaseCondition(int _valueOffset, int _offsetTrim) {
        super(_offsetTrim);
        valueOffset = _valueOffset;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        ip_.setBlock(getFirstChild());
        ExecTemplates.setVisited(ip_,this);
    }

}
