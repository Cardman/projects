package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecIfCondition extends ExecCondition implements WithEl {

    private final String label;

    public ExecIfCondition(int _conditionOffset, String _label, CustList<ExecOperationNode> _opCondition) {
        super(_conditionOffset, _opCondition);
        label = _label;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processIf(_cont,label,this,_stack, getCondition());
    }

}
