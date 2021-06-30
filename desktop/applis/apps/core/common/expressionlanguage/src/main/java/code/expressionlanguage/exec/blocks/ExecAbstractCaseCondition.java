package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public abstract class ExecAbstractCaseCondition extends ExecBracedBlock implements
        StackableBlock {

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.setBlock(getFirstChild());
        ExecHelperBlocks.setVisited(ip_,this);
    }

}
