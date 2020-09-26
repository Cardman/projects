package code.expressionlanguage.exec.blocks;

public abstract class ExecAbstractInstanceTypeCaseCondition extends ExecAbstractInstanceCaseCondition {
    private String importedClassName;

    protected ExecAbstractInstanceTypeCaseCondition(String _variableName, String _importedClassName, int _valueOffset, int _offsetTrim) {
        super(_variableName,_valueOffset, _offsetTrim);
        importedClassName = _importedClassName;
    }

    public String getImportedClassName() {
        return importedClassName;
    }
}
