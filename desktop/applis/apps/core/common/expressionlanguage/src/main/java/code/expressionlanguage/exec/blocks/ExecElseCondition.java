package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.stacks.IfBlockStack;
import code.expressionlanguage.files.OffsetsBlock;

public final class ExecElseCondition extends ExecBracedBlock implements StackableBlock {

    public ExecElseCondition(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processEl(ContextEl _cont) {
        ExecTemplates.processElse(_cont,this);
    }
}
