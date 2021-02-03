package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.StringList;
import code.util.StringMap;

public final class ScopeFilterType {
    private final ClassMethodIdAncestor id;
    private final boolean accessFromSuper;
    private final boolean superClass;
    private final boolean retRef;
    private final MethodAccessKind kind;
    private final int anc;
    private final StringList superTypesBase;
    private final StringMap<String> superTypesBaseAncBis;
    private final String formatted;
    private final String fullName;
    private final String glClass;
    private final FormattedFilter formattedFilter;

    public ScopeFilterType(ScopeFilter _scope, MethodAccessKind _kind, int _anc, StringList _superTypesBase, StringMap<String> _superTypesBaseAncBis, String _formatted, String _fullName, FormattedFilter _formattedFilter) {
        accessFromSuper = _scope.isAccessFromSuper();
        superClass = _scope.isSuperClass();
        retRef = _scope.isRetRef();
        id = _scope.getId();
        glClass = _scope.getGlClass();
        kind = _kind;
        anc = _anc;
        superTypesBase = _superTypesBase;
        superTypesBaseAncBis = _superTypesBaseAncBis;
        formatted = _formatted;
        fullName = _fullName;
        formattedFilter = _formattedFilter;
    }

    public ClassMethodIdAncestor getId() {
        return id;
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

    public boolean isAccessFromSuper() {
        return accessFromSuper;
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

    public String getFormatted() {
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
