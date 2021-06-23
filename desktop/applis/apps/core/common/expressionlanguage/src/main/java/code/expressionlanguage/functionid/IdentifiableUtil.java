package code.expressionlanguage.functionid;


import code.expressionlanguage.common.StringExpUtil;
import code.util.StringList;
import code.util.core.StringUtil;

public final class IdentifiableUtil {

    private IdentifiableUtil() {
    }

    public static StringList params(Identifiable _this) {
        StringList params_ = new StringList();
        int nbParams_ = _this.getParametersTypesLength();
        for (int i = 0; i < nbParams_; i++) {
            String p_ = _this.getParametersType(i);
            params_.add(p_);
        }
        return params_;
    }
    public static StringList incomplete(Identifiable _this) {
        StringList params_ = new StringList();
        int nbParams_ = _this.getParametersTypesLength();
        for (int i = 0; i < nbParams_; i++) {
            String p_ = _this.getParametersType(i);
            if (p_.contains("#")) {
                params_.add("");
            } else {
                params_.add(p_);
            }
        }
        return params_;
    }
    public static void appendLeftPart(int _start,StringList _paramsReturn, MethodId _id) {
        int len_ = _id.getParametersTypesLength();
        for (int i = _start; i < len_; i++) {
            String p_ = _id.getParametersType(i);
            if (i + 1 == len_ && _id.isVararg()) {
                p_ = StringExpUtil.getPrettyArrayType(p_);
            }
            if (_id.getParametersRef(i)) {
                _paramsReturn.add("~"+p_);
            } else {
                _paramsReturn.add(p_);
            }
        }
    }

    public static boolean eqPartial(Identifiable _this,Identifiable _other) {
        if (_this.isVararg() != _other.isVararg()) {
            return false;
        }
        int len_ = _this.getParametersTypesLength();
        if (len_ != _other.getParametersTypesLength()) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            if (_this.getParametersRef(i) != _other.getParametersRef(i)) {
                return false;
            }
            String param_ = _this.getParametersType(i);
            String paramOther_ = _other.getParametersType(i);
            if (!StringUtil.eq(param_,paramOther_)) {
                return false;
            }
        }
        return true;
    }
}
