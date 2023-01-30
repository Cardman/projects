package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecAbstractInstanceCaseCondition extends ExecAbstractCaseCondition {
    private final String importedClassName;

    private final String variableName;

    public ExecAbstractInstanceCaseCondition(String _variableName, String _importedClassName, CustList<ExecOperationNode> _list, int _offset) {
        super(_list, _offset);
        variableName = _variableName;
        importedClassName = _importedClassName;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.removeRefVar(variableName);
    }

    public String getImportedClassName() {
        return importedClassName;
    }
    public String getVariableName() {
        return variableName;
    }
}
