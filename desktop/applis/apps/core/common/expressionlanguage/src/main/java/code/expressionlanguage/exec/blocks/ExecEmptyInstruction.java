package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecEmptyInstruction extends ExecLeaf implements WithEl {
    public ExecEmptyInstruction(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        processBlock(_cont, _stack);
    }
}
