package code.expressionlanguage.opers.util;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Templates;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.ints.Equallable;

public final class ConstructorId implements Equallable<ConstructorId>, Identifiable {

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

    public ConstructorId format(String _genericClass, Analyzable _classes) {
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.format(_genericClass, n_, _classes);
            pTypes_.add(new ClassName(formatted_, i + 1 == len_ && isVararg()));
        }
        return new ConstructorId(_genericClass, pTypes_);
    }

    public String getSignature() {
        StringList classNames_ = new StringList();
        for (ClassName c: classNames) {
            if (c.isVararg()) {
                classNames_.add(StringList.concat(c.getName(),VARARG));
            } else {
                classNames_.add(c.getName());
            }
        }
        return StringList.concat(name,LEFT,classNames_.join(SEP_TYPE),RIGHT);
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

    @Override
    public String getName() {
        return name;
    }

    public EqList<ClassName> getClassNames() {
        return new EqList<ClassName>(classNames);
    }

    @Override
    public boolean isStaticMethod() {
        return false;
    }
}
