package code.expressionlanguage.opers.util;

public final class ClassFormattedMethodId {

    private final String className;

    private final MethodId constraints;

    public ClassFormattedMethodId(String _className, MethodId _constraints) {
        className = _className;
        constraints = _constraints;
    }

    public String getClassName() {
        return className;
    }

    public MethodId getConstraints() {
        return constraints;
    }

}
