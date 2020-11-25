package code.expressionlanguage.functionid;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.stds.DisplayedStrings;
import code.util.BooleanList;
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
    private final BooleanList refParams;
    private final boolean vararg;

    public ConstructorId(String _name, StringList _classNames, boolean _vararg) {
        name = StringUtil.nullToEmpty(_name);
        vararg = _vararg;
        refParams = new BooleanList();
        classNames = new StringList();
        for (String s: _classNames) {
            classNames.add(StringUtil.nullToEmpty(s));
            refParams.add(false);
        }
    }
    public ConstructorId(String _name, StringList _classNames,BooleanList _refParams, boolean _vararg) {
        name = StringUtil.nullToEmpty(_name);
        vararg = _vararg;
        refParams = new BooleanList();
        classNames = new StringList();
        int min_ = Math.min(_classNames.size(),_refParams.size());
        for (String s: _classNames.left(min_)) {
            classNames.add(StringUtil.nullToEmpty(s));
        }
        for (boolean s: _refParams.left(min_)) {
            refParams.add(s);
        }
    }
    public static ConstructorId to(String _access, StringList _params,ConstructorId _id) {
        return new ConstructorId(_access,_params,_id.refParams, _id.vararg);
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
        return new ConstructorId(_genericClass, pTypes_,refParams, isVararg());
    }

    public ConstructorId copy(String _class) {
        return new ConstructorId(_class, classNames,refParams, vararg);
    }

    public boolean isRef() {
        for (boolean r: refParams) {
            if (r) {
                return true;
            }
        }
        return false;
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
        StringList cls_ = new StringList();
        int m_ = Math.min(classNames.size(),refParams.size());
        for (int i = 0; i < m_; i++) {
            String s_ = "";
            if (refParams.get(i)) {
                s_ = "~";
            }
            cls_.add(StringUtil.concat(s_,classNames.get(i)));
        }
        return StringUtil.concat(name,LEFT, StringUtil.join(cls_, SEP_TYPE),suf_,RIGHT);
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
    public boolean getParametersRef(int _index) {
        return refParams.get(_index);
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
