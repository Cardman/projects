package code.expressionlanguage.exec.inherits;

import code.util.StringList;

public final class ParametersTypes {
    private StringList typesAll;
    private StringList namesAll;
    private boolean varargAll;


    public StringList getTypesAll() {
        return typesAll;
    }

    public void setTypesAll(StringList _types) {
        this.typesAll = _types;
    }

    public StringList getNamesAll() {
        return namesAll;
    }

    public void setNamesAll(StringList _names) {
        this.namesAll = _names;
    }

    public boolean isVarargAll() {
        return varargAll;
    }

    public void setVarargAll(boolean _vararg) {
        this.varargAll = _vararg;
    }

}
