package code.formathtml.exec.blocks;

import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class RendAbstractInstanceCaseCondition extends RendAbstractCaseCondition {
    private final String importedClassName;

    private final String variableName;

    public RendAbstractInstanceCaseCondition(String _variableName, String _importedClassName, CustList<RendDynOperationNode> _list, int _offset) {
        super(_list, _offset);
        variableName = _variableName;
        importedClassName = _importedClassName;
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        _ip.removeRefVar(getVariableName());
    }

    public String getImportedClassName() {
        return importedClassName;
    }
    public String getVariableName() {
        return variableName;
    }
}
