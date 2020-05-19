package code.expressionlanguage.opers.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.util.StringList;
import code.util.ints.Equallable;

public final class ClassMethodId implements Equallable<ClassMethodId> {

    private final String className;

    private final MethodId constraints;

    public ClassMethodId(String _className, MethodId _constraints) {
        className = _className;
        constraints = _constraints;
    }

    public String formatType(String _type, ContextEl _conf) {
        if (getConstraints().getKind() == MethodAccessKind.STATIC_CALL) {
            return _conf.getLastPage().formatVarType(_type,_conf);
        }
        return _type;
    }

    public String formatType(String _owner, String _formatted,ContextEl _conf) {
        if (getConstraints().getKind() == MethodAccessKind.STATIC_CALL) {
            return Templates.quickFormat(_owner, _formatted, _conf);
        }
        return _formatted;
    }

    public String getClassName() {
        return className;
    }

    public MethodId getConstraints() {
        return constraints;
    }

    @Override
    public boolean eq(ClassMethodId _g) {
        if (!StringList.quickEq(className, _g.className)) {
            return false;
        }
        return constraints.eq(_g.constraints);
    }

}
