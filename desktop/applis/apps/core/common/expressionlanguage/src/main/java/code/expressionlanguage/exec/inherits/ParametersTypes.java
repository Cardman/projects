package code.expressionlanguage.exec.inherits;

import code.util.StringList;

public final class ParametersTypes {
    private StringList types;
    private StringList names;
    private StringList typesRef;
    private StringList namesRef;
    private boolean varargRef;
    private boolean vararg;

    public StringList getTypes() {
        return types;
    }

    public void setTypes(StringList _types) {
        this.types = _types;
    }

    public StringList getNames() {
        return names;
    }

    public void setNames(StringList _names) {
        this.names = _names;
    }

    public StringList getTypesRef() {
        return typesRef;
    }

    public void setTypesRef(StringList _typesRef) {
        this.typesRef = _typesRef;
    }

    public StringList getNamesRef() {
        return namesRef;
    }

    public void setNamesRef(StringList _namesRef) {
        this.namesRef = _namesRef;
    }

    public boolean isVararg() {
        return vararg;
    }

    public void setVararg(boolean _vararg) {
        this.vararg = _vararg;
    }

    public boolean isVarargRef() {
        return varargRef;
    }

    public void setVarargRef(boolean _varargRef) {
        this.varargRef = _varargRef;
    }
}
