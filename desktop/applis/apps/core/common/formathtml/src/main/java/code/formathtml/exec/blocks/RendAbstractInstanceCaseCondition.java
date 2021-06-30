package code.formathtml.exec.blocks;

import code.formathtml.ImportingPage;

public final class RendAbstractInstanceCaseCondition extends RendAbstractCaseCondition {
    private final String importedClassName;
    private final boolean specific;

    private final String variableName;

    public RendAbstractInstanceCaseCondition(String _variableName, String _importedClassName, boolean _spec) {
        variableName = _variableName;
        importedClassName = _importedClassName;
        specific = _spec;
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        _ip.removeRefVar(getVariableName());
    }

    public boolean isSpecific(){
        return specific;
    }
    public String getImportedClassName() {
        return importedClassName;
    }
    public String getVariableName() {
        return variableName;
    }
}
