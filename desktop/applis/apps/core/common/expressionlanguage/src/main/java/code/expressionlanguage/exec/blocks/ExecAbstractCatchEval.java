package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public abstract class ExecAbstractCatchEval extends ExecBracedBlock implements WithEl {

    private final ExecOperationNodeListOff condition;

    protected ExecAbstractCatchEval(CustList<ExecOperationNode> _list, int _offset) {
        condition = new ExecOperationNodeListOff(_list, _offset);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processCatch(_cont, _stack, this, condition);
    }

}
