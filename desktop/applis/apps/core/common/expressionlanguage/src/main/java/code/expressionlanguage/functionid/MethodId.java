package code.expressionlanguage.functionid;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.FormattedMethodId;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.stds.DisplayedStrings;
import code.util.BooleanList;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class MethodId implements Identifiable {

    private static final String EMPTY = "";
    private static final String VARARG_DOTS = "...";
    private static final String SEP_TYPE = ",";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";

    private final MethodAccessKind kind;

    private final boolean retRef;

    private final String name;

    private final CustList<Boolean> refParams;
    private final StringList classNames;

    private final boolean vararg;

    public MethodId(MethodAccessKind _staticMethod, String _name, StringList _classNames) {
        this(_staticMethod, _name, _classNames, false);
    }

    public MethodId(MethodAccessKind _staticMethod, String _name, StringList _classNames, boolean _vararg) {
        retRef = false;
        kind = _staticMethod;
        vararg = _vararg;
        name = StringUtil.nullToEmpty(_name);
        refParams = new CustList<Boolean>();
        classNames = new StringList();
        feedParamTypes(_classNames);
    }

    public MethodId(boolean _retRef, MethodAccessKind _staticMethod, String _name, StringList _classNames, CustList<Boolean> _refParam, boolean _vararg) {
        retRef = _retRef;
        kind = _staticMethod;
        vararg = _vararg;
        name = StringUtil.nullToEmpty(_name);
        refParams = new CustList<Boolean>();
        classNames = new StringList();
        feedParamTypes(_classNames,_refParam);
    }

    private void feedParamTypes(StringList _classNames) {
        for (String s: _classNames) {
            classNames.add(StringUtil.nullToEmpty(s));
            refParams.add(false);
        }
    }

    private void feedParamTypes(StringList _classNames, CustList<Boolean> _refParams) {
        int min_ = Math.min(_classNames.size(),_refParams.size());
        for (String s: _classNames.left(min_)) {
            classNames.add(StringUtil.nullToEmpty(s));
        }
        for (boolean s: _refParams.left(min_)) {
            refParams.add(s);
        }
    }

    public static FormattedMethodId to(MethodId _id) {
        return new FormattedMethodId(_id.retRef, _id.name, _id.classNames,_id.refParams, _id.vararg);
    }

    public static ConstructorId to(String _access,MethodId _id) {
        return new ConstructorId(_access,_id.classNames,_id.refParams, _id.vararg);
    }

    public static MethodId to(MethodAccessKind _access, StringList _params,MethodId _id) {
        return new MethodId(_id.retRef, _access, _id.name, _params,_id.refParams, _id.vararg);
    }

    public static MethodId to(MethodAccessKind _access, String _name,MethodId _id) {
        return new MethodId(_id.retRef, _access, _name, _id.classNames,_id.refParams, _id.vararg);
    }
    public MethodId prepend(String _name,String _type, boolean _ref) {
        StringList types_ = new StringList(classNames);
        CustList<Boolean> refs_ = new CustList<Boolean>(refParams);
        types_.add(0,_type);
        refs_.add(0,_ref);
        return new MethodId(retRef, kind, _name, types_,refs_, vararg);
    }

    public static MethodAccessKind getKind(MethodAccessKind _context, MethodAccessKind _mod) {
        if (_context == MethodAccessKind.STATIC) {
            return MethodAccessKind.STATIC;
        }
        if (_mod == MethodAccessKind.STATIC) {
            return MethodAccessKind.STATIC;
        }
        if (_context == MethodAccessKind.STATIC_CALL) {
            return MethodAccessKind.STATIC_CALL;
        }
        if (_mod == MethodAccessKind.STATIC_CALL) {
            return MethodAccessKind.STATIC_CALL;
        }
        return MethodAccessKind.INSTANCE;
    }
    public static MethodAccessKind getKind(MethodModifier _mod) {
        if (_mod == MethodModifier.STATIC) {
            return MethodAccessKind.STATIC;
        }
        if (_mod == MethodModifier.STATIC_CALL) {
            return MethodAccessKind.STATIC_CALL;
        }
        return MethodAccessKind.INSTANCE;
    }
    public static MethodAccessKind getKind(boolean _static) {
        if (_static) {
            return MethodAccessKind.STATIC;
        }
        return MethodAccessKind.INSTANCE;
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
        String retRef_ = EMPTY;
        if (retRef) {
            retRef_ = "~";
        }
        String pref_ = EMPTY;
        if (kind == MethodAccessKind.STATIC) {
            pref_ = StringUtil.concat(_ana.getStaticString()," ");
        } else {
            if (kind == MethodAccessKind.STATIC_CALL) {
                pref_ = StringUtil.concat(_ana.getStaticCallString()," ");
            }
        }
        String suf_ = EMPTY;
        if (vararg) {
            suf_ = VARARG_DOTS;
        }
        StringList cls_ = new StringList();
        int m_ = Math.min(classNames.size(),refParams.size());
        for (int i = 0; i < m_; i++) {
            String s_ = "";
            if (getParametersRef(i)) {
                s_ = "~";
            }
            cls_.add(StringUtil.concat(s_,classNames.get(i)));
        }
        return StringUtil.concat(retRef_,pref_,name,LEFT, StringUtil.join(cls_, SEP_TYPE),suf_,RIGHT);
    }

    public boolean isRef() {
        if (retRef) {
            return true;
        }
        for (boolean r: refParams) {
            if (r) {
                return true;
            }
        }
        return false;
    }
    public boolean eq(MethodId _obj) {
        if (!StringUtil.quickEq(_obj.name, name)) {
            return false;
        }
        return eqPartial(_obj);
    }

    public boolean eqPartial(MethodId _other) {
        if (retRef != _other.retRef) {
            return false;
        }
        if (kind != _other.kind) {
            return false;
        }
        return IdentifiableUtil.eqPartial(this,_other);
    }

    public MethodId reflectFormat(ExecFormattedRootBlock _genericClass) {
        String name_ = getName();
        int len_ = classNames.size();
        StringList pTypes_ = new StringList();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = classNames.get(i);
            String formatted_ = ExecInherits.reflectFormat(_genericClass, n_);
            pTypes_.add(formatted_);
        }
        return new MethodId(retRef, kind, name_, pTypes_, refParams,isVararg());
    }
    public MethodId quickFormat(AnaFormattedRootBlock _root) {
        StringList pTypes_ = getFormattedTypes(_root);
        return new MethodId(retRef, kind, name, pTypes_, refParams,isVararg());
    }

    public FormattedMethodId quickOverrideFormat(AnaFormattedRootBlock _root) {
        StringList pTypes_ = getFormattedTypes(_root);
        return new FormattedMethodId(retRef, name, pTypes_,refParams, isVararg());
    }

    private StringList getFormattedTypes(AnaFormattedRootBlock _root) {
        int len_ = classNames.size();
        StringList pTypes_ = new StringList();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = classNames.get(i);
            String formatted_ = AnaInherits.quickFormat(_root, n_);
            pTypes_.add(formatted_);
        }
        return pTypes_;
    }


    @Override
    public String getName() {
        return name;
    }

    public boolean canAccessParamTypes() {
        return kind != MethodAccessKind.STATIC;
    }

    public boolean isRetRef() {
        return retRef;
    }

    @Override
    public boolean isStaticMethod() {
        return kind != MethodAccessKind.INSTANCE;
    }

    public MethodAccessKind getKind() {
        return kind;
    }

    @Override
    public StringList getParametersTypes() {
        return new StringList(classNames);
    }
    public StringList shiftFirst() {
        return new StringList(classNames.mid(1));
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

}
