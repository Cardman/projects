package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;

public final class ExecCatchEval extends ExecAbstractCatchEval {

    private final String importedClassName;

    private final String variableName;

    public ExecCatchEval(String _variableName, String _importedClassName, int _offsetTrim) {
        super(_offsetTrim);
        variableName = _variableName;
        importedClassName = _importedClassName;
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
