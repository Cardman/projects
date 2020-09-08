package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.CustList;

public final class ExecElseIfCondition extends ExecCondition implements StackableBlock {

    public ExecElseIfCondition(OffsetsBlock _offset, int _conditionOffset, CustList<ExecOperationNode> _opCondition) {
        super(_offset, _conditionOffset, _opCondition);
    }

    @Override
    public void processEl(ContextEl _cont) {
        ExecTemplates.processElseIf(_cont,this);
    }
}
