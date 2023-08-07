package code.expressionlanguage.functionid;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.stds.StandardMethod;
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

    public static boolean eq(NamedFunctionBlock _f1, StandardMethod _m1, NamedFunctionBlock _f2, StandardMethod _m2) {
        return _f1 == _f2 && _m1 == _m2;
    }

}
