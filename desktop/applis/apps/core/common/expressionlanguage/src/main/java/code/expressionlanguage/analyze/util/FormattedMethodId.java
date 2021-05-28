package code.expressionlanguage.analyze.util;
import code.util.BooleanList;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class FormattedMethodId {


    private final boolean retRef;
    private final String name;

    private final StringList classNames;
    private final CustList<Boolean> refParams;
    private final boolean vararg;

    public FormattedMethodId(boolean _retRef, String _name, StringList _classNames, CustList<Boolean> _refParam, boolean _vararg) {
        retRef = _retRef;
        vararg = _vararg;
        name = StringUtil.nullToEmpty(_name);
        refParams = _refParam;
        classNames = new StringList();
        feedParamTypes(_classNames);
    }

    private void feedParamTypes(StringList _classNames) {
        for (String s: _classNames) {
            classNames.add(StringUtil.nullToEmpty(s));
        }
    }
    private boolean getParametersRef(int _index) {
        return refParams.get(_index);
    }
    public boolean eq(FormattedMethodId _obj) {
        if (!StringUtil.quickEq(_obj.name, name)) {
            return false;
        }
        return eqPartial(_obj);
    }

    public boolean eqPartial(FormattedMethodId _other) {
        if (retRef != _other.retRef) {
            return false;
        }
        int len_ = classNames.size();
        if (len_ != _other.classNames.size()) {
            return false;
        }
        if (vararg != _other.vararg) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            if (getParametersRef(i) != _other.getParametersRef(i)) {
                return false;
            }
            String param_ = classNames.get(i);
            String paramOther_ = _other.classNames.get(i);
            if (!StringUtil.eq(param_,paramOther_)) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }
}
