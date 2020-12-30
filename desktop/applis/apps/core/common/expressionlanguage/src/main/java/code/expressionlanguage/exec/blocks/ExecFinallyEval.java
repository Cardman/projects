package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;

public final class ExecFinallyEval extends ExecBracedBlock implements StackableBlock {

    public ExecFinallyEval(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecTemplates.processFinally(_cont,this, _stack);
    }
}
