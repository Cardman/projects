package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecIfCondition extends ExecCondition implements WithEl {

    private final String label;

    public ExecIfCondition(String _label, ExecOperationNodeListOff _ex) {
        super(_ex);
        label = _label;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processIf(_cont,label,this,_stack, getCondition());
    }

}
