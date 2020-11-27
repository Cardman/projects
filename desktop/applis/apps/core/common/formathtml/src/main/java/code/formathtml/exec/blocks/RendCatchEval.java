package code.formathtml.exec.blocks;

import code.formathtml.ImportingPage;

public final class RendCatchEval extends RendAbstractCatchEval {

    private String importedClassName;

    private String variableName;
    public RendCatchEval(String _className, String _variable, int _offsetTrim) {
        super(_offsetTrim);
        importedClassName = _className;
        variableName = _variable;
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
        _ip.removeLocalVar(var_);
    }

}
