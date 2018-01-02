package code.expressionlanguage.opers.util;

import code.util.EqList;
import code.util.StringList;
import code.util.ints.Equallable;

public final class FctConstraints implements Equallable<FctConstraints> {
    private static final String SEP_AND_TYPE = "&";
    private static final String SEP_TYPE = ",";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";

    private final String name;

    private final EqList<StringList> constraints;

    public FctConstraints(String _name,
            EqList<StringList> _constraints) {
        name = _name;
        constraints = _constraints;
    }
    public String getSignature() {
        StringList classNames_ = new StringList();
        for (StringList c: constraints) {
            classNames_.add(c.join(SEP_AND_TYPE));
        }
        return StringList.concat(name,LEFT,classNames_.join(SEP_TYPE),RIGHT);
    }
    @Override
    public boolean eq(FctConstraints _c) {
        if (!StringList.quickEq(_c.name, name)) {
            return false;
        }
        if (constraints.size() != _c.constraints.size()) {
            return false;
        }
        int len_ = _c.constraints.size();
        for (int i = 0; i < len_; i++) {
            StringList cOne_ = constraints.get(i);
            StringList cTwo_ = _c.constraints.get(i);
            if (!StringList.equalsSet(cOne_, cTwo_)) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public EqList<StringList> getConstraints() {
        return constraints;
    }
}
