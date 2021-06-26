package code.expressionlanguage.exec.blocks;

public final class ExecAbstractInstanceTypeCaseCondition extends ExecAbstractInstanceCaseCondition {
    private final String importedClassName;
    private final boolean specific;

    public ExecAbstractInstanceTypeCaseCondition(String _variableName, String _importedClassName, int _valueOffset, boolean _spec) {
        super(_variableName,_valueOffset);
        importedClassName = _importedClassName;
        specific = _spec;
    }

    public boolean isSpecific(){
        return specific;
    }
    public String getImportedClassName() {
        return importedClassName;
    }
}
