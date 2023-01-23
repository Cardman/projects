package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public abstract class ExecAbstractCaseCondition extends ExecBracedBlock implements
        WithEl {

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.setVisitedCase(_cont,_stack, this);
    }

}
