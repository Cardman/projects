package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecElseIfCondition extends ExecCondition implements WithEl {

    public ExecElseIfCondition(ExecOperationNodeListOff _ex) {
        super(_ex);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processElseIf(_cont,this, _stack,getCondition());
    }
}
