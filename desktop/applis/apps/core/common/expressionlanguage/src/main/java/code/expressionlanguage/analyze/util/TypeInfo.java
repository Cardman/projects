package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.stds.StandardType;
import code.util.StringList;

public final class TypeInfo {
    private final String typeId;
    private final AnaGeneType root;
    private final MethodAccessKind scope;
    private final int ancestor;
    private final boolean base;
    private final StringList superTypes;
    private final AnaFormattedRootBlock formatted;

    public TypeInfo(RootBlock _type, String _formatted, MethodAccessKind _scope, boolean _base, int _anc) {
        this.formatted = new AnaFormattedRootBlock(_type,_formatted);
        root = _type;
        typeId = StringExpUtil.getIdFromAllTypes(_formatted);
        superTypes = _type.getAllSuperTypes();
        scope = _scope;
        base = _base;
        ancestor = _anc;
    }

    public TypeInfo(StandardType _type, String _formatted, MethodAccessKind _scope, boolean _base) {
        this.formatted = new AnaFormattedRootBlock(null,_formatted);
        root = _type;
        typeId = _formatted;
        superTypes = _type.getAllSuperTypes();
        scope = _scope;
        base = _base;
        ancestor = 0;
    }

    public AnaFormattedRootBlock getFormatted() {
        return formatted;
    }

    public AnaGeneType getRoot() {
        return root;
    }

    public String getTypeId() {
        return typeId;
    }

    public MethodAccessKind getScope() {
        return scope;
    }

    public int getAncestor() {
        return ancestor;
    }

    public boolean isBase() {
        return base;
    }

    public StringList getSuperTypes() {
        return superTypes;
    }

}
