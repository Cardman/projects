package code.expressionlanguage.analyze.util;

import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.StringList;

public final class TypeInfo {
    private String type;
    private MethodAccessKind scope;
    private int ancestor;
    private boolean base;
    private StringList superTypes = new StringList();

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }

    public MethodAccessKind getScope() {
        return scope;
    }

    public void setScope(MethodAccessKind scope) {
        this.scope = scope;
    }

    public int getAncestor() {
        return ancestor;
    }

    public void setAncestor(int _ancestor) {
        ancestor = _ancestor;
    }

    public boolean isBase() {
        return base;
    }

    public void setBase(boolean base) {
        this.base = base;
    }

    public StringList getSuperTypes() {
        return superTypes;
    }

    public void setSuperTypes(StringList superTypes) {
        this.superTypes = superTypes;
    }
}
