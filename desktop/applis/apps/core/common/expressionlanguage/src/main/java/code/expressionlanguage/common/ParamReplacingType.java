package code.expressionlanguage.common;

import code.util.StringMap;
import code.util.core.StringUtil;

public final class ParamReplacingType extends AbstractReplacingType {
    private final String objType;
    public ParamReplacingType(String _objType) {
        objType = _objType;
    }
    @Override
    protected boolean replace(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i) {
        int diese_ = getDiese();
        String sub_ = _type.substring(diese_+1, _i);
        String value_ = _varTypes.getVal(sub_);
        if (value_ == null) {
            sub_ = _type.substring(diese_, _i);
            _str.append(sub_);
            return false;
        }
        int max_ = _str.length() -1;
        int j_ = AbstractReplacingType.getMaxIndex(_str, max_);
        if (StringUtil.quickEq(value_, SUB_TYPE)) {
            if (AbstractReplacingType.isSupChar(_str, j_)) {
                _str.delete(j_, max_+1);
                _str.append(objType);
                return false;
            }
            return true;
        }
        if (value_.startsWith(SUB_TYPE)) {
            String bound_= value_.substring(SUB_TYPE.length());
            if (AbstractReplacingType.isSupChar(_str, j_)) {
                _str.append(bound_);
                return false;
            }
            return true;
        }
        if (value_.startsWith(SUP_TYPE)) {
            String bound_= value_.substring(SUP_TYPE.length());
            if (AbstractReplacingType.isSubChar(_str, j_)) {
                _str.append(bound_);
                return false;
            }
            if (AbstractReplacingType.isSupChar(_str, j_)) {
                _str.delete(j_, max_+1);
                _str.append(objType);
                return false;
            }
            return true;
        }
        _str.append(value_);
        return false;
    }
}
