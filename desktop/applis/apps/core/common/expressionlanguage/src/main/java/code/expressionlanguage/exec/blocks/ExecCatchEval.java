package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;

public final class ExecCatchEval extends ExecAbstractCatchEval {

    private String importedClassName;

    private final String variableName;

    public ExecCatchEval(OffsetsBlock _offset, String _variableName, String _importedClassName) {
        super(_offset);
        variableName = _variableName;
        importedClassName = _importedClassName;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getValueVars().removeKey(variableName);
    }

    public String getVariableName() {
        return variableName;
    }
}
