package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecElseIfCondition extends ExecCondition implements StackableBlock {

    public ExecElseIfCondition(int _conditionOffset, CustList<ExecOperationNode> _opCondition, int _offsetTrim) {
        super(_conditionOffset, _opCondition, _offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecTemplates.processElseIf(_cont,this, _stack);
    }
}
