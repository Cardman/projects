package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecAbstractInstanceCaseCondition extends ExecAbstractCaseCondition {
    private final String importedClassName;
    private final boolean specific;

    private final String variableName;

    private final ExecOperationNodeListOff exp;

    private final int index;

    public ExecAbstractInstanceCaseCondition(String _variableName, String _importedClassName, boolean _spec, CustList<ExecOperationNode> _list, int _offset, int _index) {
        variableName = _variableName;
        importedClassName = _importedClassName;
        specific = _spec;
        exp = new ExecOperationNodeListOff(_list, _offset);
        index = _index;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.removeRefVar(variableName);
    }

    public ExecOperationNodeListOff getExp() {
        return exp;
    }

    public int getIndex() {
        return index;
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
