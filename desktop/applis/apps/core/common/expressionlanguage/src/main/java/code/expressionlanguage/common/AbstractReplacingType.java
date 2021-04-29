package code.expressionlanguage.common;

import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class AbstractReplacingType {
    public static final String ARR_CLASS = "[";
    public static final char ARR_BEG = '[';
    public static final String ARR_BEG_STRING = "[";
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final char SEP_CLASS_CHAR = '.';
    public static final char SUB_TYPE_CHAR = '?';
    public static final char SUP_TYPE_CHAR = '!';
    public static final String SUB_TYPE = "?";
    public static final String SUP_TYPE = "!";
    public static final char PREFIX_VAR_TYPE_CHAR = '#';
    public static final String INNER_TYPE = "..";

    public static final char LT = '<';

    public static final char GT = '>';

    public static final char COMMA = ',';

    protected String formattedType(String _type, StringMap<String> _varTypes) {
        if (_varTypes.isEmpty()) {
            return _type;
        }
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            char ch_ = _type.charAt(i);
            if (ch_ == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(ch_);
                continue;
            }
            if (StringExpUtil.isDollarWordChar(ch_)) {
                continue;
            }
            var_ = false;
            if (replace(_type, _varTypes, str_,i, diese_)) {
                return "";
            }
            str_.append(ch_);
        }
        if (var_) {
            replace(_type, _varTypes, str_,len_, diese_);
        }
        return str_.toString();
    }
    protected static void tryReplaceType(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i, int _diese) {
        String sub_ = _type.substring(_diese + 1, _i);
        String val_ = _varTypes.getVal(sub_);
        if (val_ != null) {
            tryReplaceType(_str, val_);
        } else {
            sub_ = _type.substring(_diese, _i);
            _str.append(sub_);
        }
    }

    protected static void tryReplaceType(StringBuilder _str, String _value) {
        int j_ = getMaxIndex(_str, _str.length() - 1);
        if (_value.startsWith(SUB_TYPE)) {
            if (!isSubOrSubChar(_str, j_)) {
                _str.insert(j_ + 1, SUB_TYPE);
            }
            _str.append(_value.substring(SUB_TYPE.length()));
        } else if (_value.startsWith(SUP_TYPE)) {
            if (!isSubOrSubChar(_str, j_)) {
                _str.insert(j_ + 1, SUP_TYPE);
            }
            _str.append(_value.substring(SUP_TYPE.length()));
        } else {
            _str.append(_value);
        }
    }

    protected static void replaceReflectedType(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i, int _diese) {
        String sub_ = _type.substring(_diese + 1, _i);
        String value_ = _varTypes.getVal(sub_);
        if (value_ != null) {
            replaceReflectedType(_str, value_);
        } else {
            sub_ = _type.substring(_diese, _i);
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

    protected static int getMaxIndex(StringBuilder _str, int _max) {
        int j_ = _max;
        while (j_ >= 0) {
            if (_str.charAt(j_) != ARR_BEG) {
                break;
            }
            j_--;
        }
        return j_;
    }

    protected static boolean isNotChar(StringBuilder _str, int _j, char _subTypeChar) {
        return _j >= 0 && _str.charAt(_j) != _subTypeChar;
    }

    protected static boolean isSubOrSubChar(StringBuilder _str, int _j) {
        return isSubChar(_str,_j) || isSupChar(_str, _j);
    }

    protected static boolean isSubChar(StringBuilder _str, int _j) {
        return _j >= 0 && _str.charAt(_j) == SUB_TYPE_CHAR;
    }

    protected static boolean isSupChar(StringBuilder _str, int _j) {
        return _j >= 0 && _str.charAt(_j) == SUP_TYPE_CHAR;
    }

    public abstract boolean replace(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i, int _diese);
}
