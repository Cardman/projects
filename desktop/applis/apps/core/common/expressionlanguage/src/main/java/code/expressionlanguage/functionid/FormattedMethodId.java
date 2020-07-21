package code.expressionlanguage.functionid;
import code.util.StringList;

public final class FormattedMethodId {

    private final String name;

    private final StringList classNames;

    private final boolean vararg;

    public FormattedMethodId(String _name, StringList _classNames, boolean _vararg) {
        vararg = _vararg;
        name = _name;
        classNames = new StringList();
        feedParamTypes(_classNames);
    }

    private void feedParamTypes(StringList _classNames) {
        for (String s: _classNames) {
            classNames.add(s);
        }
    }

    public boolean eq(FormattedMethodId _obj) {
        if (!StringList.quickEq(_obj.name, name)) {
            return false;
        }
        return eqPartial(_obj);
    }

    public boolean eqPartial(FormattedMethodId _other) {
        int len_ = classNames.size();
        if (len_ != _other.classNames.size()) {
            return false;
        }
        if (vararg != _other.vararg) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            String param_ = classNames.get(i);
            String paramOther_ = _other.classNames.get(i);
            if (!StringList.eq(param_,paramOther_)) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }
}
