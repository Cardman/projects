package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;

public final class ScopeFilter {
    private final ClassMethodIdAncestor id;
    private final boolean accessFromSuper;
    private final boolean superClass;
    private final boolean retRef;
    private final String glClass;

    public ScopeFilter(ClassMethodIdAncestor _id, boolean _accessFromSuper, boolean _superClass, boolean _retRef,
                       String _glClass) {
        this.id = _id;
        this.accessFromSuper = _accessFromSuper;
        this.superClass = _superClass;
        this.retRef = _retRef;
        this.glClass = _glClass;
    }

    public boolean isSuperClass() {
        return superClass;
    }

    public boolean isAccessFromSuper() {
        return accessFromSuper;
    }

    public boolean isRetRef() {
        return retRef;
    }

    public String getGlClass() {
        return glClass;
    }

    public ClassMethodIdAncestor getId() {
        return id;
    }
}
