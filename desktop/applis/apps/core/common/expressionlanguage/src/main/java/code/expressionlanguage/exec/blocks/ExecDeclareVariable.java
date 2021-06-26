package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.util.StringList;

public final class ExecDeclareVariable extends ExecAbstractDeclareVariable {

    private final String importedClassName;

    public ExecDeclareVariable(String _importedClassName, StringList _variableNames) {
        super(_variableNames);
        importedClassName = _importedClassName;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processDeclare(_cont, _stack, importedClassName, this);
    }

    @Override
    public void removeLocalVars(AbstractPageEl _ip) {
        for (String v: getVariableNames()) {
            _ip.removeRefVar(v);
        }
    }

}
