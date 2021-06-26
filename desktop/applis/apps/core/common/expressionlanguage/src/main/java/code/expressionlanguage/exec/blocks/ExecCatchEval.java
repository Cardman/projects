package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public final class ExecCatchEval extends ExecAbstractCatchEval {

    private final String importedClassName;

    private final String variableName;

    public ExecCatchEval(String _variableName, String _importedClassName) {
        variableName = _variableName;
        importedClassName = _importedClassName;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        procCatch(_cont, _stack);
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.removeRefVar(variableName);
    }

    public String getVariableName() {
        return variableName;
    }
}
