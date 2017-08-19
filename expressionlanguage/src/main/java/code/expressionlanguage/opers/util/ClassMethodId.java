package code.expressionlanguage.opers.util;
import code.util.ints.Equallable;

public final class ClassMethodId implements Equallable<ClassMethodId> {

    private final ClassName className;

    private final FctConstraints constraints;

    public ClassMethodId(ClassName _className, FctConstraints _constraints) {
        className = _className;
        constraints = _constraints;
    }

    public ClassName getClassName() {
        return className;
    }

    public FctConstraints getConstraints() {
        return constraints;
    }

    @Override
    public boolean eq(ClassMethodId _g) {
        if (!className.eq(_g.className)) {
            return false;
        }
        if (!constraints.eq(_g.constraints)) {
            return false;
        }
        return true;
    }

}
