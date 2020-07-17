package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.files.OffsetsBlock;

public abstract class ExecAbstractInstanceTypeCaseCondition extends ExecAbstractInstanceCaseCondition {
    private String importedClassName;

    public ExecAbstractInstanceTypeCaseCondition(OffsetsBlock _offset, String _variableName, String _importedClassName, int _valueOffset) {
        super(_offset,_variableName,_valueOffset);
        importedClassName = _importedClassName;
    }

    public String getImportedClassName() {
        return importedClassName;
    }
}
