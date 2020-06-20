package code.expressionlanguage.functionid;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
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
    
    public ConstructorId reflectFormat(String _genericClass, ContextEl _classes) {
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = ExecTemplates.reflectFormat(_genericClass, n_, _classes);
            pTypes_.add(formatted_);
        }
        return new ConstructorId(_genericClass, pTypes_, isVararg());
    }

    @Override
    public String getSignature(ContextEl _ana) {
        String suf_ = EMPTY;
        if (vararg) {
            suf_ = VARARG;
        }
        return StringList.concat(name,LEFT, StringList.join(classNames, SEP_TYPE),suf_,RIGHT);
    }

    @Override
    public boolean eq(ConstructorId _obj) {
        return IdentifiableUtil.eqPartial(this,_obj);
    }

    @Override
    public StringList getParametersTypes() {
        return new StringList(classNames);
    }

    @Override
    public int getParametersTypesLength() {
        return classNames.size();
    }

    @Override
    public String getParametersType(int _index) {
        return classNames.get(_index);
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
