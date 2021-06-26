package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecUnclassedBracedBlock extends ExecBracedBlock implements WithEl {

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processUnclassed(_cont, _stack, this);
    }

}
