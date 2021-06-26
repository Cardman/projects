package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecElseIfCondition extends ExecCondition implements StackableBlock {

    public ExecElseIfCondition(int _conditionOffset, CustList<ExecOperationNode> _opCondition) {
        super(_conditionOffset, _opCondition);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processElseIf(_cont,this, _stack,getCondition());
    }
}
