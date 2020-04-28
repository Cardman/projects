package code.expressionlanguage.options;

import code.util.StringList;

public final class Options {

    private StringList typesInit = new StringList();
    private boolean readOnly;
    private boolean failIfNotAllInit;

    public StringList getTypesInit() {
        return typesInit;
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
