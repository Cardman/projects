package code.expressionlanguage.common;

import code.util.StringMap;

public final class TryReplacingType extends AbstractReplacingType {

    @Override
    protected boolean replace(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i) {
        return tryReplaceType(_type, _varTypes, _str, _i);
    }
    private boolean tryReplaceType(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i) {
        int diese_ = getDiese();
        String sub_ = _type.substring(diese_ + 1, _i);
        String val_ = _varTypes.getVal(sub_);
        if (val_ != null) {
            return tryReplaceType(_str, val_);
        }
        _str.append('#');
        _str.append(sub_);
        return false;
    }

    private boolean tryReplaceType(StringBuilder _str, String _value) {
        int j_ = getMaxIndex(_str, _str.length() - 1);
        if (_value.startsWith(SUB_TYPE)) {
            if (koType(_str, j_)) {
                return true;
            }
            _str.insert(j_ + 1, SUB_TYPE);
            _str.append(_value.substring(SUB_TYPE.length()));
        } else if (_value.startsWith(SUP_TYPE)) {
            if (koType(_str, j_)) {
                return true;
            }
            _str.insert(j_ + 1, SUP_TYPE);
            _str.append(_value.substring(SUP_TYPE.length()));
        } else {
            _str.append(_value);
        }
        return false;
    }

    private boolean koType(StringBuilder _str, int _j) {
        return isSubOrSubChar(_str, _j) || !isVariable();
    }

}
