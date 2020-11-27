package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;

public abstract class ExecAbstractInstanceCaseCondition extends ExecAbstractCaseCondition {

    private final String variableName;

    protected ExecAbstractInstanceCaseCondition(String _variableName, int _valueOffset, int _offsetTrim) {
        super(_valueOffset, _offsetTrim);
        variableName = _variableName;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        if (!variableName.isEmpty()) {
            _ip.removeLocalVar(variableName);
        }
    }

    public String getVariableName() {
        return variableName;
    }
}
