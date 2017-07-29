package code.expressionlanguage.opers.util;

import code.util.EqList;
import code.util.StringList;
import code.util.ints.Equallable;

public class FctConstraints implements Equallable<FctConstraints> {

    private final String name;

    private final EqList<StringList> constraints;

    public FctConstraints(String _name,
            EqList<StringList> _constraints) {
        name = _name;
        constraints = _constraints;
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
