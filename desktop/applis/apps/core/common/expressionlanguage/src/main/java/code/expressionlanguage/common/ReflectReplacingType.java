package code.expressionlanguage.common;

import code.util.StringMap;
import code.util.core.StringUtil;

public final class ReflectReplacingType extends AbstractReplacingType {
    @Override
    protected boolean replace(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i) {
        replaceReflectedType(_type, _varTypes, _str, _i);
        return false;
    }

    private void replaceReflectedType(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i) {
        int diese_ = getDiese();
        String sub_ = _type.substring(diese_ + 1, _i);
        String value_ = _varTypes.getVal(sub_);
        if (value_ != null) {
            replaceReflectedType(_str, value_);
        } else {
            sub_ = _type.substring(diese_, _i);
            _str.append(sub_);
        }
    }

    private static void replaceReflectedType(StringBuilder _str, String _value) {
        int j_ = getMaxIndex(_str, _str.length() - 1);
        if (StringUtil.quickEq(_value, SUB_TYPE)) {
            _str.delete(j_+1,_str.length());
            _str.append(_value);
        } else if (_value.startsWith(SUB_TYPE)) {
            if (isNotChar(_str,j_, SUB_TYPE_CHAR) && isNotChar(_str,j_, SUP_TYPE_CHAR)) {
                _str.insert(j_ +1, SUB_TYPE);
            }
            _str.append(_value.substring(SUB_TYPE.length()));
        } else if (_value.startsWith(SUP_TYPE)) {
            if (isNotChar(_str,j_, SUB_TYPE_CHAR) && isNotChar(_str,j_, SUP_TYPE_CHAR)) {
                _str.insert(j_ +1, SUP_TYPE);
            }
            _str.append(_value.substring(SUP_TYPE.length()));
        } else {
            _str.append(_value);
        }
    }
}
