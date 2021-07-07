package code.expressionlanguage.functionid;
import code.expressionlanguage.common.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ConstructorId extends AbsractIdentifiableCommon implements Identifiable {

    private static final String EMPTY = "";
    private static final String CST_VARARG = "...";
    private static final String SEP_TYPE = ",";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";

    public ConstructorId(String _name, CustList<String> _classNames, boolean _vararg) {
        super(StringUtil.nullToEmpty(_name),_vararg);
        feedParamTypes(_classNames);
    }
    public ConstructorId(String _name, CustList<String> _classNames, CustList<Boolean> _refParams, boolean _vararg) {
        super(StringUtil.nullToEmpty(_name),_vararg);
        feedParamTypes(_classNames, _refParams);
    }

    public static MethodId to(ConstructorId _id) {
        return new MethodId(false, MethodAccessKind.INSTANCE, _id.getName(), _id.getClassNames(), _id.getRefParams(), _id.isVararg());
    }

    public static ConstructorId to(String _access, CustList<String> _params,ConstructorId _id) {
        return new ConstructorId(_access,_params, _id.getRefParams(), _id.isVararg());
    }

    public ConstructorId reflectFormat(StringMap<String> _varTypes, String _formatted) {
        int len_ = getParametersTypesLength();
        CustList<String> pTypes_ = new CustList<String>();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = getParametersType(i);
            String formatted_ = StringExpUtil.getReflectFormattedType(n_, _varTypes);
            pTypes_.add(formatted_);
        }
        return new ConstructorId(_formatted, pTypes_, getRefParams(), isVararg());
    }

    public ConstructorId copy(String _class) {
        return new ConstructorId(_class, getClassNames(), getRefParams(), isVararg());
    }

    @Override
    public String getSignature(DisplayedStrings _ana) {
        String suf_ = EMPTY;
        if (isVararg()) {
            suf_ = CST_VARARG;
        }
        CustList<String> cls_ = new CustList<String>();
        int m_ = Math.min(getClassNames().size(), getRefParams().size());
        for (int i = 0; i < m_; i++) {
            String s_ = "";
            if (getParametersRef(i)) {
                s_ = "~";
            }
            cls_.add(StringUtil.concat(s_,getClassNames().get(i)));
        }
        return StringUtil.concat(getName(),LEFT, StringUtil.join(cls_, SEP_TYPE),suf_,RIGHT);
    }

    public boolean eq(ConstructorId _obj) {
        return IdentifiableUtil.eqPartial(this,_obj);
    }

    @Override
    public boolean isStaticMethod() {
        return false;
    }
}
