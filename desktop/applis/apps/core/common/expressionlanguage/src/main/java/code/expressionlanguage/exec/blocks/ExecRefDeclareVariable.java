package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.util.StringList;

public final class ExecRefDeclareVariable extends ExecAbstractDeclareVariable {

    public ExecRefDeclareVariable(int _offsetTrim, StringList _variableNames) {
        super(_variableNames,_offsetTrim);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        processBlock(_cont, _stack);
    }

    @Override
    public void removeLocalVars(AbstractPageEl _ip) {
        for (String v: getVariableNames()) {
            _ip.removeRefVar(v);
        }
    }
}
