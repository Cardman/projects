package code.expressionlanguage.analyze.util;

import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.StringList;

public final class TypeInfo {
    private String type;
    private String typeId;
    private AnaGeneType root;
    private MethodAccessKind scope;
    private int ancestor;
    private boolean base;
    private StringList superTypes = new StringList();

    public AnaGeneType getRoot() {
        return root;
    }

    public void setRoot(AnaGeneType _root) {
        root = _root;
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String _typeId) {
        typeId = _typeId;
    }

    public MethodAccessKind getScope() {
        return scope;
    }

    public void setScope(MethodAccessKind _scope) {
        this.scope = _scope;
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

    public void setBase(boolean _base) {
        this.base = _base;
    }

    public StringList getSuperTypes() {
        return superTypes;
    }

    public void setSuperTypes(StringList _superTypes) {
        this.superTypes = _superTypes;
    }
}
