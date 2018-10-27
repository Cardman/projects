package code.expressionlanguage;

public final class Options {

    private boolean initializeStaticClassFirst = true;
    private boolean quickCompare = true;
    private VariableSuffix suffixVar = VariableSuffix.NONE;
    private boolean varTypeFirst = true;
    private boolean upperLong;
    private boolean endLineSemiColumn;

    public char getEndLine() {
        if (endLineSemiColumn) {
            return ';';
        }
        return ':';
    }
    public char getSuffix() {
        if (!endLineSemiColumn) {
            return ';';
        }
        return ':';
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

    public boolean isUpperLong() {
        return upperLong;
    }

    public void setUpperLong(boolean _upperLong) {
        upperLong = _upperLong;
    }

    public boolean isEndLineSemiColumn() {
        return endLineSemiColumn;
    }

    public void setEndLineSemiColumn(boolean _endLineSemiColumn) {
        endLineSemiColumn = _endLineSemiColumn;
    }

}
