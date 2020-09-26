package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;

public final class ExecEmptyInstruction extends ExecLeaf implements WithEl {
    public ExecEmptyInstruction(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont) {
        processBlock(_cont);
    }
}
