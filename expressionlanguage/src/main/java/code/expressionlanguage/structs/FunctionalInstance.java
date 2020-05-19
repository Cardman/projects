package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class FunctionalInstance implements AbstractFunctionalInstance {

    private final String className;

    private Struct functional;

    public FunctionalInstance(String _className) {
        className = _className;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public Struct getFunctional() {
        return functional;
    }

    @Override
    public void setFunctional(Struct _functional) {
        functional = _functional;
    }
}
