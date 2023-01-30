package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public abstract class ExecAbstractCaseCondition extends ExecBracedBlock implements
        WithEl {

    private final ExecOperationNodeListOff exp;

    protected ExecAbstractCaseCondition(CustList<ExecOperationNode> _list, int _offset) {
        exp = new ExecOperationNodeListOff(_list, _offset);
    }
    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.setVisitedCase(_cont,_stack, this);
    }

    public ExecOperationNodeListOff getExp() {
        return exp;
    }

}
