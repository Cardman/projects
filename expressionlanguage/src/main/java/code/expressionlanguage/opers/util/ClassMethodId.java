package code.expressionlanguage.opers.util;
import code.util.StringList;
import code.util.ints.Equallable;

public final class ClassMethodId implements Equallable<ClassMethodId> {

    private final String className;

    private final FctConstraints constraints;

    public ClassMethodId(String _className, FctConstraints _constraints) {
        className = _className;
        constraints = _constraints;
    }

    public String getClassName() {
        return className;
    }

    public FctConstraints getConstraints() {
        return constraints;
    }

    @Override
    public boolean eq(ClassMethodId _g) {
        if (!StringList.quickEq(className, _g.className)) {
            return false;
        }
        if (!constraints.eq(_g.constraints)) {
            return false;
        }
        return true;
    }

}
