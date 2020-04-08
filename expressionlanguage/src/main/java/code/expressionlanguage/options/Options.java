package code.expressionlanguage.options;

import code.expressionlanguage.variables.VariableSuffix;
import code.util.StringList;

public final class Options {

    private StringList typesInit = new StringList();
    private boolean initializeStaticClassFirst = true;
    private VariableSuffix suffixVar = VariableSuffix.NONE;
    private boolean varTypeFirst = true;
    private boolean endLineSemiColumn = true;
    private boolean singleInnerParts;
    private boolean readOnly;
    private boolean failIfNotAllInit;

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

    public StringList getTypesInit() {
        return typesInit;
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

    public void setVarTypeFirst(boolean _varTypeFirst) {
        varTypeFirst = _varTypeFirst;
    }

    public void setEndLineSemiColumn(boolean _endLineSemiColumn) {
        endLineSemiColumn = _endLineSemiColumn;
    }

    public boolean isSingleInnerParts() {
        return singleInnerParts;
    }
    public void setSingleInnerParts(boolean _singleInnerParts) {
        singleInnerParts = _singleInnerParts;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean _readOnly) {
        readOnly = _readOnly;
    }

    public boolean isFailIfNotAllInit() {
        return failIfNotAllInit;
    }

    public void setFailIfNotAllInit(boolean _failIfNotAllInit) {
        failIfNotAllInit = _failIfNotAllInit;
    }
}
