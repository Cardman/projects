package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecWhileCondition extends ExecCondition implements WithEl {

    private final String label;
    public ExecWhileCondition(int _conditionOffset, String _label, CustList<ExecOperationNode> _opCondition) {
        super(_conditionOffset, _opCondition);
        label = _label;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processWhile(_cont, _stack, label, this, getCondition());
    }


}
