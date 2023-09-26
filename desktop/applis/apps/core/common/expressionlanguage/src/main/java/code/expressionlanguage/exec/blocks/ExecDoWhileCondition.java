package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecDoWhileCondition extends ExecCondition {
    public ExecDoWhileCondition(ExecOperationNodeListOff _ex) {
        super(_ex);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processDoWhile(_cont,this, _stack, getCondition());
    }
}
