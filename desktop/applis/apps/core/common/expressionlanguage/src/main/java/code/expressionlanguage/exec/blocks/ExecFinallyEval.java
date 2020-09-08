package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.files.OffsetsBlock;

public final class ExecFinallyEval extends ExecBracedBlock implements StackableBlock {

    public ExecFinallyEval(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processEl(ContextEl _cont) {
        ExecTemplates.processFinally(_cont,this);
    }
}
