package code.expressionlanguage.opers.util;
import java.lang.reflect.Method;

import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Classes;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.ints.Equallable;

public final class MethodId implements Equallable<MethodId> {

    private static final String VARARG = "...";
    private static final String SEP_TYPE = ",";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";

    private final String name;

    private final EqList<ClassName> classNames;

    public MethodId(String _name, EqList<ClassName> _classNames) {
        name = _name;
        classNames = _classNames;
    }

    public MethodId(Method _method) {
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
    public boolean eq(MethodId _obj) {
        if (!StringList.quickEq(_obj.name, name)) {
            return false;
        }
        int len_ = classNames.size();
        if (len_ != _obj.classNames.size()) {
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
        for (int i = 0; i < len_; i++) {
            if (!classNames.get(i).eq(_obj.classNames.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    public MethodId format(String _genericClass, Classes _classes) {
        String name_ = getName();
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.format(_genericClass, n_, _classes);
            pTypes_.add(new ClassName(formatted_, i + 1 == len_ && isVararg()));
        }
        return new MethodId(name_, pTypes_);
    }

    public MethodId generalFormat(String _genericClass, Classes _classes) {
        String name_ = getName();
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.generalFormat(_genericClass, n_, _classes);
            pTypes_.add(new ClassName(formatted_, i + 1 == len_ && isVararg()));
        }
        return new MethodId(name_, pTypes_);
    }

    public String getName() {
        return name;
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

    public EqList<ClassName> getClassNames() {
        return new EqList<ClassName>(classNames);
    }
}
