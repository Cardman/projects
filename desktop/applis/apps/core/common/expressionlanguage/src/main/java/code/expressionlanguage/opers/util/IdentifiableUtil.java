package code.expressionlanguage.opers.util;


import code.util.StringList;

public final class IdentifiableUtil {

    private IdentifiableUtil() {
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
            String param_ = _this.getParametersType(i);
            String paramOther_ = _other.getParametersType(i);
            if (!StringList.eq(param_,paramOther_)) {
                return false;
            }
        }
        return true;
    }
}
