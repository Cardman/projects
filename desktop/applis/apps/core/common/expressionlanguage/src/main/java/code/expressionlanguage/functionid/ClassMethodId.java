package code.expressionlanguage.functionid;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.util.core.StringUtil;

public final class ClassMethodId {

    private final String className;

    private final MethodId constraints;

    public ClassMethodId(String _className, MethodId _constraints) {
        className = StringUtil.nullToEmpty(_className);
        constraints = _constraints;
    }

    public static String formatType(String _type, ContextEl _conf, MethodAccessKind _kind) {
        if (_kind == MethodAccessKind.STATIC_CALL) {
            return _conf.formatVarType(_type);
        }
        return _type;
    }

    public static String formatType(ExecRootBlock _rootBlock, String _owner, String _formatted, MethodAccessKind _kind) {
        if (_kind == MethodAccessKind.STATIC_CALL) {
            return ExecTemplates.quickFormat(_rootBlock,_owner, _formatted);
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
