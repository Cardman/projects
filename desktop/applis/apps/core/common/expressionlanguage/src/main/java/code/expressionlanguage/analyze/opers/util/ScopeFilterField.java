package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.util.TypeInfo;

public final class ScopeFilterField {
    private final boolean aff;
    private final String name;
    private final String rootName;

    public ScopeFilterField(boolean _aff, String _name, TypeInfo _typeInfo) {
        aff = _aff;
        name = _name;
        rootName = _typeInfo.getTypeId();
    }

    public boolean isAff() {
        return aff;
    }

    public String getName() {
        return name;
    }

    public String getRootName() {
        return rootName;
    }
}
