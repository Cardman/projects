package code.expressionlanguage.common;

import code.util.StringMap;
import code.util.core.StringUtil;

public final class ReturnReplacingType extends AbstractReplacingType {
    @Override
    public boolean replace(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i, int _diese) {
        String sub_ = _type.substring(_diese+1, _i);
        String value_ = _varTypes.getVal(sub_);
        if (value_ != null) {
            int max_ = _str.length() -1;
            int j_ = AbstractReplacingType.getMaxIndex(_str, max_);
            if (StringUtil.quickEq(value_, SUB_TYPE)) {
                if (AbstractReplacingType.isSubOrSubChar(_str, j_)) {
                    j_--;
                }
                _str.delete(j_+1, max_+1);
                _str.append(SUB_TYPE);
                return false;

            }
            if (value_.startsWith(SUB_TYPE)) {
                String bound_= value_.substring(SUB_TYPE.length());
                if (AbstractReplacingType.isSupChar(_str, j_)) {
                    _str.delete(j_, max_+1);
                    _str.append(SUB_TYPE);
                    return false;
                }
                if (AbstractReplacingType.isNotChar(_str, j_, SUB_TYPE_CHAR)) {
                    _str.insert(j_ +1, SUB_TYPE);
                }
                _str.append(bound_);
                return false;

            }
            if (value_.startsWith(SUP_TYPE)) {
                String bound_= value_.substring(SUP_TYPE.length());
                if (AbstractReplacingType.isSubChar(_str, j_)) {
                    _str.delete(j_, max_+1);
                    _str.append(SUB_TYPE);
                    return false;
                }
                if (AbstractReplacingType.isNotChar(_str, j_, SUP_TYPE_CHAR)) {
                    _str.insert(j_ +1, SUP_TYPE);
                }
                _str.append(bound_);
                return false;

            }
            _str.append(value_);
        } else {
            sub_ = _type.substring(_diese, _i);
            _str.append(sub_);
        }
        return false;
    }
}
