package code.formathtml.exec.blocks;

import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class RendCatchEval extends RendAbstractCatchEval {

    private final String importedClassName;

    private final String variableName;

    private final boolean throwIfGuardError;
    public RendCatchEval(String _className, String _variable, CustList<RendDynOperationNode> _ls, int _off, boolean _thr) {
        super(_ls, _off);
        importedClassName = _className;
        variableName = _variable;
        throwIfGuardError = _thr;
    }

    public String getImportedClassName() {
        return importedClassName;
    }
    public String getVariableName() {
        return variableName;
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        String var_ = getVariableName();
        _ip.removeRefVar(var_);
    }

    public boolean isThrowIfGuardError() {
        return throwIfGuardError;
    }
}
