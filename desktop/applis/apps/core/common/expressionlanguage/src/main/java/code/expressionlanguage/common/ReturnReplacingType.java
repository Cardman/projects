package code.expressionlanguage.common;

import code.util.StringMap;
import code.util.core.StringUtil;

public final class ReturnReplacingType extends AbstractReplacingType {
    @Override
    protected boolean replace(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i) {
        apply(_type, _varTypes, _str, _i);
        return false;
    }
    private void apply(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i) {
        int diese_ = getDiese();
        String sub_ = _type.substring(diese_+1, _i);
        String value_ = _varTypes.getVal(sub_);
        if (value_ == null) {
            sub_ = _type.substring(diese_, _i);
            _str.append(sub_);
            return;
        }
        int max_ = _str.length() -1;
        int j_ = getMaxIndex(_str, max_);
        if (StringUtil.quickEq(value_, SUB_TYPE)) {
            if (isSubOrSubChar(_str, j_)) {
                j_--;
            }
            _str.delete(j_+1, max_+1);
            _str.append(SUB_TYPE);
            return;
        }
        if (value_.startsWith(SUB_TYPE)) {
            String bound_= value_.substring(SUB_TYPE.length());
            if (isSupChar(_str, j_)) {
                _str.delete(j_, max_+1);
                _str.append(SUB_TYPE);
                return;
            }
            if (isNotChar(_str, j_, SUB_TYPE_CHAR)) {
                _str.insert(j_ +1, SUB_TYPE);
            }
            _str.append(bound_);
            return;

        }
        if (value_.startsWith(SUP_TYPE)) {
            String bound_= value_.substring(SUP_TYPE.length());
            if (isSubChar(_str, j_)) {
                _str.delete(j_, max_+1);
                _str.append(SUB_TYPE);
                return;
            }
            if (isNotChar(_str, j_, SUP_TYPE_CHAR)) {
                _str.insert(j_ +1, SUP_TYPE);
            }
            _str.append(bound_);
            return;

        }
        _str.append(value_);
    }
}
