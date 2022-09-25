package code.expressionlanguage.functionid;
import code.expressionlanguage.common.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class MethodId extends AbsractIdentifiableCommon {

    private static final String EMPTY = "";
    private static final String VARARG_DOTS = "...";
    private static final String SEP_TYPE = ",";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";

    private final MethodAccessKind kind;

    private final boolean retRef;

    public MethodId(MethodAccessKind _staticMethod, String _name, CustList<String> _classNames) {
        this(_staticMethod, _name, _classNames, false);
    }

    public MethodId(MethodAccessKind _staticMethod, String _name, CustList<String> _classNames, boolean _vararg) {
        super(StringUtil.nullToEmpty(_name),_vararg);
        retRef = false;
        kind = _staticMethod;
        feedParamTypes(_classNames);
    }

    public MethodId(boolean _retRef, MethodAccessKind _staticMethod, String _name, CustList<String> _classNames, CustList<BoolVal> _refParam, boolean _vararg) {
        super(StringUtil.nullToEmpty(_name),_vararg);
        retRef = _retRef;
        kind = _staticMethod;
        feedParamTypes(_classNames,_refParam);
    }

    public static ConstructorId to(String _access,MethodId _id) {
        return new ConstructorId(_access, _id.getClassNames(), _id.getRefParams(), _id.isVararg());
    }

    public static MethodId to(MethodAccessKind _access, CustList<String> _params,MethodId _id) {
        return new MethodId(_id.retRef, _access, _id.getName(), _params, _id.getRefParams(), _id.isVararg());
    }

    public static MethodId to(MethodAccessKind _access, String _name,MethodId _id) {
        return new MethodId(_id.retRef, _access, _name, _id.getClassNames(), _id.getRefParams(), _id.isVararg());
    }
    public MethodId prepend(String _name,String _type, BoolVal _ref) {
        CustList<String> types_ = new CustList<String>(getClassNames());
        CustList<BoolVal> refs_ = new CustList<BoolVal>(getRefParams());
        types_.add(0,_type);
        refs_.add(0,_ref);
        return new MethodId(retRef, kind, _name, types_,refs_, isVararg());
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
        if (isVararg()) {
            suf_ = VARARG_DOTS;
        }
        CustList<String> cls_ = new CustList<String>();
        int m_ = NumberUtil.min(getClassNames().size(), getRefParams().size());
        for (int i = 0; i < m_; i++) {
            String s_ = "";
            if (getParametersRef(i) == BoolVal.TRUE) {
                s_ = "~";
            }
            cls_.add(StringUtil.concat(s_, getClassNames().get(i)));
        }
        return StringUtil.concat(retRef_,pref_, getName(),LEFT, StringUtil.join(cls_, SEP_TYPE),suf_,RIGHT);
    }

    public boolean isRef() {
        if (retRef) {
            return true;
        }
        for (BoolVal r: getRefParams()) {
            if (r == BoolVal.TRUE) {
                return true;
            }
        }
        return false;
    }
    public boolean eq(MethodId _obj) {
        if (!StringUtil.quickEq(_obj.getName(), getName())) {
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

    public MethodId reflectFormat(StringMap<String> _varTypes) {
        String name_ = getName();
        int len_ = getClassNames().size();
        CustList<String> pTypes_ = new CustList<String>();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = getClassNames().get(i);
            String formatted_ = StringExpUtil.getReflectFormattedType(n_, _varTypes);
            pTypes_.add(formatted_);
        }
        return new MethodId(retRef, kind, name_, pTypes_, getRefParams(),isVararg());
    }
    public MethodId quickFormat(StringMap<String> _varTypes) {
        CustList<String> pTypes_ = getFormattedTypes(_varTypes);
        return new MethodId(retRef, kind, getName(), pTypes_, getRefParams(),isVararg());
    }

    private CustList<String> getFormattedTypes(StringMap<String> _varTypes) {
        int len_ = getClassNames().size();
        CustList<String> pTypes_ = new CustList<String>();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = getClassNames().get(i);
            String formatted_ = StringExpUtil.getQuickFormattedType(n_, _varTypes);
            pTypes_.add(formatted_);
        }
        return pTypes_;
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

    public CustList<String> shiftFirst() {
        return getClassNames().mid(1);
    }

}
