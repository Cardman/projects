package code.expressionlanguage.opers.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Templates;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.ints.Equallable;

public final class MethodId implements Equallable<MethodId>, Identifiable {

    private static final String VARARG = "...";
    private static final String SEP_TYPE = ",";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";

    private final boolean staticMethod;

    private final String name;

    private final EqList<ClassName> classNames;

    public MethodId(boolean _staticMethod,String _name, EqList<ClassName> _classNames) {
        staticMethod = _staticMethod;
        name = _name;
        classNames = _classNames;
    }


    public MethodId(MethodModifier _staticMethod,String _name, StringList _classNames) {
        this(_staticMethod, _name, _classNames, false);
    }

    public MethodId(MethodModifier _staticMethod,String _name, StringList _classNames, boolean _vararg) {
        staticMethod = _staticMethod == MethodModifier.STATIC;
        name = _name;
        EqList<ClassName> classNames_ = new EqList<ClassName>();
        int i_ = CustList.FIRST_INDEX;
        int c_ = _classNames.size();
        for (String s: _classNames) {
            classNames_.add(new ClassName(s, _vararg && i_ == c_ - 1));
            i_++;
        }
        classNames = classNames_;
    }

    public String getSignature() {
        StringList classNames_ = new StringList();
        for (ClassName c: classNames) {
            if (c.isVararg()) {
                classNames_.add(c.getName()+VARARG);
            } else {
                classNames_.add(c.getName());
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
        if (staticMethod != _obj.staticMethod) {
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
    
    public MethodId format(String _genericClass, ContextEl _context) {
        String name_ = getName();
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.format(_genericClass, n_, _context);
            pTypes_.add(new ClassName(formatted_, i + 1 == len_ && isVararg()));
        }
        return new MethodId(isStaticMethod(), name_, pTypes_);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isStaticMethod() {
        return staticMethod;
    }

    @Override
    public StringList getParametersTypes() {
        StringList params_ = new StringList();
        for (ClassName c: classNames) {
            params_.add(c.getName());
        }
        return params_;
    }

    @Override
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
