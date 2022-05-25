package code.expressionlanguage.analyze.files;

public final class OffsetClassVariableInfo {
    private final OffsetStringInfo className;
    private final OffsetStringInfo variableName;

    public OffsetClassVariableInfo(OffsetStringInfo _cl, OffsetStringInfo _varName) {
        this.className = _cl;
        this.variableName = _varName;
    }

    public OffsetStringInfo getClassName() {
        return className;
    }

    public OffsetStringInfo getVariableName() {
        return variableName;
    }
}
