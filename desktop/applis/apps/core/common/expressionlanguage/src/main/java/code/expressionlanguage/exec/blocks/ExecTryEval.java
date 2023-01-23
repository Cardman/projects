package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecTryEval extends ExecBracedBlock implements WithEl {

    private final String label;
    public ExecTryEval(String _label) {
        label= _label;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processTry(_cont, _stack, label, this);
    }

}
