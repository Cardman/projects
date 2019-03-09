package code.expressionlanguage.options;

import code.expressionlanguage.variables.VariableSuffix;

public final class Options {

    private boolean initializeStaticClassFirst = true;
    private VariableSuffix suffixVar = VariableSuffix.NONE;
    private boolean varTypeFirst = true;
    private boolean upperLong;
    private boolean endLineSemiColumn = true;
    private boolean allParametersSort = true;
    private boolean singleInnerParts;

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

    public VariableSuffix getSuffixVar() {
        return suffixVar;
    }

    public void setSuffixVar(VariableSuffix _suffixVar) {
        suffixVar = _suffixVar;
    }

    public boolean isVarTypeFirst() {
        return varTypeFirst;
    }

    public boolean isUpperLong() {
        return upperLong;
    }

    public void setUpperLong(boolean _upperLong) {
        upperLong = _upperLong;
    }

    public void setEndLineSemiColumn(boolean _endLineSemiColumn) {
        endLineSemiColumn = _endLineSemiColumn;
    }

    public boolean isAllParametersSort() {
        return allParametersSort;
    }

    public void setAllParametersSort(boolean _allParametersSort) {
        allParametersSort = _allParametersSort;
    }

    public boolean isSingleInnerParts() {
        return singleInnerParts;
    }
    public void setSingleInnerParts(boolean _singleInnerParts) {
        singleInnerParts = _singleInnerParts;
    }

}
