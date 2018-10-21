package code.expressionlanguage;

public final class Options {

    private boolean multipleAffectations = true;
    private boolean initializeStaticClassFirst = true;
    private boolean quickCompare = true;
    private VariableSuffix suffixVar = VariableSuffix.NONE;
    private boolean varTypeFirst = true;

    public boolean isMultipleAffectations() {
        return multipleAffectations;
    }

    public void setMultipleAffectations(boolean _multipleAffectations) {
        multipleAffectations = _multipleAffectations;
    }

    public boolean isInitializeStaticClassFirst() {
        return initializeStaticClassFirst;
    }

    public void setInitializeStaticClassFirst(boolean _initializeStaticClassFirst) {
        initializeStaticClassFirst = _initializeStaticClassFirst;
    }

    public boolean isQuickCompare() {
        return quickCompare;
    }

    public void setQuickCompare(boolean _quickCompare) {
        quickCompare = _quickCompare;
    }

    public VariableSuffix getSuffixVar() {
        return suffixVar;
    }

    public void setSuffixVar(VariableSuffix _suffixVar) {
        suffixVar = _suffixVar;
    }

    public boolean isVarTypeFirst() {
        return varTypeFirst;
    }

    public void setVarTypeFirst(boolean _varTypeFirst) {
        varTypeFirst = _varTypeFirst;
    }

}
