package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecWhileCondition extends ExecCondition implements WithEl {

    private final String label;
    public ExecWhileCondition(String _label, ExecOperationNodeListOff _ex) {
        super(_ex);
        label = _label;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processWhile(_cont, _stack, label, this, getCondition());
    }


}
