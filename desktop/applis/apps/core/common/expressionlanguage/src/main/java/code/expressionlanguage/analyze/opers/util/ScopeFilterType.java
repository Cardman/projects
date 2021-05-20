package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.TypeInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.StringList;
import code.util.StringMap;

public final class ScopeFilterType {
    private final ClassMethodIdAncestor id;
    private final boolean baseClass;
    private final boolean superClass;
    private final boolean retRef;
    private final MethodAccessKind kind;
    private final int anc;
    private final StringList superTypesBase;
    private final StringMap<String> superTypesBaseAncBis;
    private final TypeInfo typeInfo;
    private final AnaFormattedRootBlock formatted;
    private final String fullName;
    private final String glClass;
    private final FormattedFilter formattedFilter;

    public ScopeFilterType(ScopeFilter _scope, TypeInfo _typeInfo, MethodAccessKind _kind, StringList _superTypesBase, StringMap<String> _superTypesBaseAncBis, FormattedFilter _formattedFilter) {
        baseClass = _scope.isBaseClass();
        superClass = _scope.isSuperClass();
        retRef = _scope.isRetRef();
        id = _scope.getId();
        glClass = _scope.getGlClass();
        kind = _kind;
        typeInfo = _typeInfo;
        anc = _typeInfo.getAncestor();
        superTypesBase = _superTypesBase;
        superTypesBaseAncBis = _superTypesBaseAncBis;
        formatted = _typeInfo.getFormatted();
        fullName = _typeInfo.getTypeId();
        formattedFilter = _formattedFilter;
    }

    public ClassMethodIdAncestor getId() {
        return id;
    }

    public TypeInfo getTypeInfo() {
        return typeInfo;
    }

    public int getAnc() {
        return anc;
    }

    public String getGlClass() {
        return glClass;
    }

    public boolean isRetRef() {
        return retRef;
    }

    public boolean isBaseClass() {
        return baseClass;
    }

    public boolean isSuperClass() {
        return superClass;
    }

    public StringList getSuperTypesBase() {
        return superTypesBase;
    }

    public StringMap<String> getSuperTypesBaseAncBis() {
        return superTypesBaseAncBis;
    }

    public AnaFormattedRootBlock getFormatted() {
        return formatted;
    }

    public String getFullName() {
        return fullName;
    }

    public FormattedFilter getFormattedFilter() {
        return formattedFilter;
    }

    public MethodAccessKind getKind() {
        return kind;
    }
}
