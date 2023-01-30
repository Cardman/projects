package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecCatchEval extends ExecAbstractCatchEval {

    private final String importedClassName;

    private final String variableName;

    private final boolean throwIfGuardError;
    private final boolean catchAll;
    public ExecCatchEval(String _variableName, String _importedClassName, CustList<ExecOperationNode> _ls, int _off, boolean _thr, boolean _ca) {
        super(new ExecOperationNodeListOff(_ls, _off));
        variableName = _variableName;
        importedClassName = _importedClassName;
        throwIfGuardError = _thr;
        catchAll = _ca;
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

    public boolean isThrowIfGuardError() {
        return throwIfGuardError;
    }

    public boolean isCatchAll() {
        return catchAll;
    }
}
