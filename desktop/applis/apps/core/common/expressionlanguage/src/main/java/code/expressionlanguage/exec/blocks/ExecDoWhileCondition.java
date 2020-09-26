package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecDoWhileCondition extends ExecCondition {
    public ExecDoWhileCondition(int _conditionOffset, CustList<ExecOperationNode> _opCondition, int _offsetTrim) {
        super(_conditionOffset, _opCondition, _offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont) {
        ExecTemplates.processDo(_cont,this);
    }
}
