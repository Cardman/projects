package code.expressionlanguage.opers.util;
import java.lang.reflect.Constructor;

import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.ints.Equallable;

public final class ConstructorId implements Equallable<ConstructorId> {

    private static final String VARARG = "...";
    private static final String SEP_TYPE = ",";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";

    private final String name;

    private final EqList<ClassName> classNames;

    public ConstructorId(String _name, EqList<ClassName> _classNames) {
        name = _name;
        classNames = _classNames;
    }

    public ConstructorId(Constructor<?> _method) {
        name = _method.getName();
        classNames = new EqList<ClassName>();
        boolean varargMeth_ = _method.isVarArgs();
        Class<?>[] classes_ = _method.getParameterTypes();
        int len_ = classes_.length;
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            Class<?> param_ = classes_[i];
            boolean vararg_ = varargMeth_ && i + 1 == len_;
            classNames.add(new ClassName(param_.getName(), vararg_));
        }
    }

    public String getSignature() {
        StringList classNames_ = new StringList();
        for (ClassName c: classNames) {
            classNames_.add(c.getName());
            if (c.isVararg()) {
                classNames_.add(VARARG);
            }
        }
        return name+LEFT+classNames_.join(SEP_TYPE)+RIGHT;
    }

    @Override
    public boolean eq(ConstructorId _obj) {
        if (!StringList.quickEq(_obj.name, name)) {
            return false;
        }
        if (classNames.size() != _obj.classNames.size()) {
            return false;
        }
        if (!classNames.isEmpty()) {
            if (classNames.last().isVararg()) {
                if (!_obj.classNames.last().isVararg()) {
                    return false;
                }
            } else {
                if (_obj.classNames.last().isVararg()) {
                    return false;
                }
            }
        }
        int len_ = classNames.size();
        for (int i = 0; i < len_; i++) {
            if (!classNames.get(i).eq(_obj.classNames.get(i))) {
                return false;
            }
        }
        return true;
    }

    public StringList getParametersTypes() {
        StringList params_ = new StringList();
        for (ClassName c: classNames) {
            params_.add(c.getName());
        }
        return params_;
    }

    public boolean isVararg() {
        if (classNames.isEmpty()) {
            return false;
        }
        return classNames.last().isVararg();
    }

    public String getName() {
        return name;
    }

    public EqList<ClassName> getClassNames() {
        return new EqList<ClassName>(classNames);
    }
}
