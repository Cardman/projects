package code.expressionlanguage.functionid;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.stds.DisplayedStrings;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ConstructorId implements Identifiable {

    private static final String EMPTY = "";
    private static final String VARARG = "...";
    private static final String SEP_TYPE = ",";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";

    private final String name;

    private final StringList classNames;

    private final boolean vararg;

    public ConstructorId(String _name, StringList _classNames, boolean _vararg) {
        name = StringUtil.nullToEmpty(_name);
        vararg = _vararg;
        classNames = new StringList();
        for (String s: _classNames) {
            classNames.add(StringUtil.nullToEmpty(s));
        }
    }
    public static ConstructorId to(String _access, StringList _params,ConstructorId _id) {
        return new ConstructorId(_access,_params, _id.vararg);
    }
    public ConstructorId reflectFormat(String _genericClass, ContextEl _classes) {
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = ExecTemplates.reflectFormat(_genericClass, n_, _classes);
            pTypes_.add(formatted_);
        }
        return new ConstructorId(_genericClass, pTypes_, isVararg());
    }

    public ConstructorId copy(String _class) {
        return new ConstructorId(_class, classNames, vararg);
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getSignature(_ana.getStandards().getDisplayedStrings());
    }
    public String getSignature(AnalyzedPageEl _ana) {
        return getSignature(_ana.getDisplayedStrings());
    }
    @Override
    public String getSignature(DisplayedStrings _ana) {
        String suf_ = EMPTY;
        if (vararg) {
            suf_ = VARARG;
        }
        return StringUtil.concat(name,LEFT, StringUtil.join(classNames, SEP_TYPE),suf_,RIGHT);
    }

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
