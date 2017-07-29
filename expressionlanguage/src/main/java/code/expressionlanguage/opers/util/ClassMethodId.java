package code.expressionlanguage.opers.util;
import code.util.ints.Equallable;

public final class ClassMethodId implements Equallable<ClassMethodId> {

    private final ClassName className;
    
    private final MethodId method;

    private final FctConstraints constraints;

    public ClassMethodId(ClassName _className, MethodId _method, FctConstraints _constraints) {
        className = _className;
        method = _method;
        constraints = _constraints;
    }

    public ClassName getClassName() {
        return className;
    }

    public MethodId getMethod() {
        return method;
    }

    public FctConstraints getConstraints() {
        return constraints;
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
