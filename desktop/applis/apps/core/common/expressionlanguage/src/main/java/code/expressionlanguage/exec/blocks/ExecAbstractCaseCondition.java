package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public abstract class ExecAbstractCaseCondition extends ExecBracedBlock implements
        StackableBlock {

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.setVisited(_stack, this);
    }

}
