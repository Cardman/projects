package code.expressionlanguage.functionid;
import code.util.core.StringUtil;

public final class ClassMethodId {

    private final String className;

    private final MethodId constraints;

    public ClassMethodId(String _className, MethodId _constraints) {
        className = StringUtil.nullToEmpty(_className);
        constraints = _constraints;
    }

    public String getClassName() {
        return className;
    }

    public MethodId getConstraints() {
        return constraints;
    }

    public boolean eq(ClassMethodId _g) {
        if (!StringUtil.quickEq(className, _g.className)) {
            return false;
        }
        return constraints.eq(_g.constraints);
    }

}
