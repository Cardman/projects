package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;

public final class ExecElseCondition extends ExecBracedBlock implements StackableBlock {

    public ExecElseCondition(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont) {
        ExecTemplates.processElse(_cont,this);
    }
}
