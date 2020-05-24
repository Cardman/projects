package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class FunctionalInstance implements AbstractFunctionalInstance {

    private final String className;

    private final LambdaStruct functional;

    public FunctionalInstance(String _className, LambdaStruct _functional) {
        className = _className;
        functional = _functional;
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
    public LambdaStruct getFunctional() {
        return functional;
    }
}
