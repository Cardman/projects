package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecElseCondition extends ExecBracedBlock implements WithEl {

    private final int off;
    public ExecElseCondition(int _o) {
        off = _o;
    }
    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        _stack.getLastPage().globalOffset(off);
        ExecHelperBlocks.processElse(_cont,this, _stack);
    }
}
