package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public abstract class ExecAbstractCatchEval extends ExecBracedBlock implements WithEl {

    private final ExecOperationNodeListOff condition;

    protected ExecAbstractCatchEval(ExecOperationNodeListOff _cond) {
        condition = _cond;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processCatch(_cont, _stack, this, condition);
    }

}
