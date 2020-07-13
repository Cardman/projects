package code.expressionlanguage.functionid;


import code.expressionlanguage.common.StringExpUtil;
import code.util.StringList;

public final class IdentifiableUtil {

    private IdentifiableUtil() {
    }
    public static void appendLeftPart(String _foundClass, boolean _demand, StringList _paramsReturn, MethodId _id, boolean _staticMethod) {
        StringList params_ = _id.getParametersTypes();
        if (!_staticMethod && _demand) {
            _paramsReturn.add(_foundClass);
        }
        if (_id.isVararg()) {
            for (String p: params_.mid(0, params_.size() - 1)) {
                _paramsReturn.add(p);
            }
            String p_ = params_.last();
            _paramsReturn.add(StringExpUtil.getPrettyArrayType(p_));
        } else {
            for (String p: params_) {
                _paramsReturn.add(p);
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
            String param_ = _this.getParametersType(i);
            String paramOther_ = _other.getParametersType(i);
            if (!StringList.eq(param_,paramOther_)) {
                return false;
            }
        }
        return true;
    }
}
