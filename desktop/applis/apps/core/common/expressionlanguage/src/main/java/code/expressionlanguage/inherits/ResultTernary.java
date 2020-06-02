package code.expressionlanguage.inherits;

import code.util.StringList;

public final class ResultTernary {

    private final StringList types;
    private final boolean unwrapFirst;
    private final boolean unwrapSecond;
    public ResultTernary(StringList _types, boolean _unwrapFirst,
            boolean _unwrapSecond) {
        types = _types;
        unwrapFirst = _unwrapFirst;
        unwrapSecond = _unwrapSecond;
    }
    public StringList getTypes() {
        return types;
    }
    public boolean isUnwrapFirst() {
        return unwrapFirst;
    }
    public boolean isUnwrapSecond() {
        return unwrapSecond;
    }

}
