package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelperBlocks;
import code.expressionlanguage.exec.StackCall;

public final class ExecElseCondition extends ExecBracedBlock implements StackableBlock {

    public ExecElseCondition(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processElse(_cont,this, _stack);
    }
}
