package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.util.CustList;

public final class ExecDoWhileCondition extends ExecCondition {
    public ExecDoWhileCondition(OffsetsBlock _offset, int _conditionOffset, CustList<ExecOperationNode> _opCondition) {
        super(_offset, _conditionOffset, _opCondition);
    }

    @Override
    public void processEl(ContextEl _cont) {
        ExecTemplates.processDo(_cont,this);
    }
}
