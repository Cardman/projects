package code.expressionlanguage.opers.util;
import code.util.ints.Equallable;

public final class ClassMethodId implements Equallable<ClassMethodId> {

    private final ClassName className;

    private final MethodId method;

    public ClassMethodId(ClassName _className, MethodId _method) {
        className = _className;
        method = _method;
    }

    public ClassName getClassName() {
        return className;
    }

    public MethodId getMethod() {
        return method;
    }

    @Override
    public boolean eq(ClassMethodId _g) {
        if (!className.eq(_g.className)) {
            return false;
        }
        if (!method.eq(_g.method)) {
            return false;
        }
        return true;
    }

}
