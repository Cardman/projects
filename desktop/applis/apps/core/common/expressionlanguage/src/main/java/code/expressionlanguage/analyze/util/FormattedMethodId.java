package code.expressionlanguage.analyze.util;
import code.expressionlanguage.functionid.IdentifiableUtil;
import code.expressionlanguage.functionid.MethodId;
import code.util.core.StringUtil;

public final class FormattedMethodId {

    private final MethodId id;

    public FormattedMethodId(MethodId _id) {
        id = _id;
    }

    public boolean eq(FormattedMethodId _obj) {
        if (!StringUtil.quickEq(_obj.id.getName(), id.getName())) {
            return false;
        }
        return eqPartial(_obj);
    }

    public static boolean eqPartial(FormattedMethodId _current,FormattedMethodId _other) {
        if (_current.getName().startsWith("[]")) {
            return _current.eq(_other);
        }
        if (_other.getName().startsWith("[]")) {
            return false;
        }
        return _current.eqPartial(_other);
    }
    public boolean eqPartial(FormattedMethodId _other) {
        if (id.isRetRef() != _other.id.isRetRef()) {
            return false;
        }
        return IdentifiableUtil.eqPartial(id, _other.id);
    }

    public String getName() {
        return id.getName();
    }
}
