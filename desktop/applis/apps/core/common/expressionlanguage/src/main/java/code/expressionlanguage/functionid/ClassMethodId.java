package code.expressionlanguage.functionid;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.util.core.StringUtil;

public final class ClassMethodId {

    private final String className;

    private final MethodId constraints;

    public ClassMethodId(String _className, MethodId _constraints) {
        className = StringUtil.nullToEmpty(_className);
        constraints = _constraints;
    }

    public static ExecFormattedRootBlock formatType(ExecFormattedRootBlock _type, MethodAccessKind _kind, StackCall _stackCall) {
        if (_kind == MethodAccessKind.STATIC_CALL) {
            return _stackCall.formatVarType(_type);
        }
        return _type;
    }

    public static String formatType(ExecFormattedRootBlock _rootBlock, String _formatted, MethodAccessKind _kind) {
        if (_kind == MethodAccessKind.STATIC_CALL) {
            return ExecInherits.quickFormat(_rootBlock, _formatted);
        }
        return _formatted;
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
