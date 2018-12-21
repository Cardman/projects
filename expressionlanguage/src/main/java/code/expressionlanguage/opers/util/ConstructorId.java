package code.expressionlanguage.opers.util;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.StringList;
import code.util.ints.Equallable;

public final class ConstructorId implements Equallable<ConstructorId>, Identifiable {

    private static final String EMPTY = "";
    private static final String VARARG = "...";
    private static final String SEP_TYPE = ",";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";

    private final String name;

    private final StringList classNames;

    private final boolean vararg;

    public ConstructorId(String _name, StringList _classNames, boolean _vararg) {
        name = _name;
        vararg = _vararg;
        classNames = new StringList();
        for (String s: _classNames) {
            classNames.add(s);
        }
    }
    
    public ConstructorId reflectFormat(String _genericClass, Analyzable _classes) {
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.reflectFormat(_genericClass, n_, _classes);
            pTypes_.add(formatted_);
        }
        return new ConstructorId(_genericClass, pTypes_, isVararg());
    }
    
    public ConstructorId quickFormat(String _genericClass, Analyzable _classes) {
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.quickFormat(_genericClass, n_, _classes);
            pTypes_.add(formatted_);
        }
        return new ConstructorId(_genericClass, pTypes_, isVararg());
    }

    @Override
    public String getSignature() {
        String suf_ = EMPTY;
        if (vararg) {
            suf_ = VARARG;
        }
        return StringList.concat(name,LEFT,classNames.join(SEP_TYPE),suf_,RIGHT);
    }

    @Override
    public boolean eq(ConstructorId _obj) {
        if (!StringList.quickEq(_obj.name, name)) {
            return false;
        }
        if (classNames.size() != _obj.classNames.size()) {
            return false;
        }
        if (vararg != _obj.vararg) {
            return false;
        }
        int len_ = classNames.size();
        for (int i = 0; i < len_; i++) {
            if (!StringList.quickEq(classNames.get(i), _obj.classNames.get(i))) {
                return false;
            }
        }
        return true;
    }
    public boolean same(ConstructorId _obj) {
        if (!StringList.quickEq(_obj.name, name)) {
            return false;
        }
        if (classNames.size() != _obj.classNames.size()) {
            return false;
        }
        if (vararg != _obj.vararg) {
            return false;
        }
        int len_ = classNames.size();
        for (int i = 0; i < len_; i++) {
            String param_ = classNames.get(i);
            String paramOther_ = _obj.classNames.get(i);
            if (param_ == null) {
                if (paramOther_ != null) {
                    return false;
                }
                continue;
            }
            if (paramOther_ == null) {
                return false;
            }
            if (!StringList.quickEq(param_,paramOther_)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public StringList getParametersTypes() {
        return new StringList(classNames);
    }

    @Override
    public boolean isVararg() {
        return vararg;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isStaticMethod() {
        return false;
    }
}
