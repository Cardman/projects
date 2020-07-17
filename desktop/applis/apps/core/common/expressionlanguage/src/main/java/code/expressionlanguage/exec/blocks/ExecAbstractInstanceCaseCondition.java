package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.files.OffsetsBlock;

public abstract class ExecAbstractInstanceCaseCondition extends ExecAbstractCaseCondition {

    private final String variableName;

    public ExecAbstractInstanceCaseCondition(OffsetsBlock _offset, String _variableName, int _valueOffset) {
        super(_offset, _valueOffset);
        variableName = _variableName;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        if (!variableName.isEmpty()) {
            _ip.getValueVars().removeKey(variableName);
        }
    }

    public String getVariableName() {
        return variableName;
    }
}
