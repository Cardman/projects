package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecTryEval extends ExecBracedBlock implements WithEl {

    private final int off;
    private final String label;
    public ExecTryEval(int _o,String _label) {
        off = _o;
        label= _label;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processTry(_stack, label, this);
    }

    public int getOff() {
        return off;
    }
}
