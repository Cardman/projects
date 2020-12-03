package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.common.AnaGeneType;

public final class ScopeFilterField {
    private final boolean aff;
    private final String name;
    private final AnaGeneType root;
    private final String rootName;

    public ScopeFilterField(boolean _aff, String _name, AnaGeneType _root, String _rootName) {
        aff = _aff;
        name = _name;
        root = _root;
        rootName = _rootName;
    }

    public boolean isAff() {
        return aff;
    }

    public String getName() {
        return name;
    }

    public AnaGeneType getRoot() {
        return root;
    }

    public String getRootName() {
        return rootName;
    }
}
