package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public abstract class ExecAbstractCatchEval extends ExecBracedBlock implements WithEl {

    protected void procCatch(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processCatch(_cont, _stack, this);
    }

}
