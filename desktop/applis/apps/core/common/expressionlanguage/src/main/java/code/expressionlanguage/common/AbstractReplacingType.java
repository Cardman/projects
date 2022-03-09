package code.expressionlanguage.common;

import code.util.StringMap;

public abstract class AbstractReplacingType {
    public static final String ARR_CLASS = "[";
    public static final char ARR_BEG = '[';
    public static final String ARR_BEG_STRING = "[";
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final char SUB_TYPE_CHAR = '?';
    public static final char SUP_TYPE_CHAR = '!';
    public static final String SUB_TYPE = "?";
    public static final String SUP_TYPE = "!";
    public static final char PREFIX_VAR_TYPE_CHAR = '#';
    public static final String INNER_TYPE = "..";

    public static final char LT = '<';

    public static final char GT = '>';
    public static final String PREFIX_VAR_TYPE_STR = "#";

    private boolean variable;
    private int diese;

    protected String formattedType(String _type, StringMap<String> _varTypes) {
        if (_varTypes.isEmpty()) {
            return _type;
        }
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        diese = 0;
        variable = false;
        for (int i = 0; i < len_; i++) {
            char ch_ = _type.charAt(i);
            if (next(ch_,i,str_)) {
                continue;
            }
            variable = false;
            if (replace(_type, _varTypes, str_,i)) {
                return "";
            }
            str_.append(ch_);
        }
        if (variable && replace(_type, _varTypes, str_, len_)) {
            return "";
        }
        return str_.toString();
    }

    private boolean next(char _ch,int _i,StringBuilder _str) {
        if (_ch == PREFIX_VAR_TYPE_CHAR) {
            variable = true;
            diese = _i;
            return true;
        }
        if (!variable) {
            _str.append(_ch);
            return true;
        }
        return StringExpUtil.isDollarWordChar(_ch);
    }
    protected int getDiese() {
        return diese;
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

    protected abstract boolean replace(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i);
}
