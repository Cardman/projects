package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecNullCatchEval extends ExecAbstractCatchEval {

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        procCatch(_cont, _stack);
    }

}
