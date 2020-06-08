package code.expressionlanguage.common;

import code.expressionlanguage.inherits.Templates;
import code.util.StringList;

public final class StringExpUtil {
    private StringExpUtil() {
    }

    public static boolean nextCharIs(String _str, int _i, int _len, char _value) {
        if (_i < 0) {
            return false;
        }
        if (_i >= _len) {
            return false;
        }
        return _str.charAt(_i) == _value;
    }

    public static int nextPrintCharIs(int _j, int _len, String _string, char _ch) {
        int j_ = nextPrintChar(_j,_len,_string);
        if (j_ < 0) {
            return -1;
        }
        if (_string.charAt(j_) != _ch) {
            return -1;
        }
        return j_;
    }

    public static boolean isDigit(char _char) {
        return _char >= '0' && _char <= '9';
    }

    public static int nextPrintChar(int _j, int _len, String _string) {
        int j_ = _j;
        while (j_ < _len) {
            if (!Character.isWhitespace(_string.charAt(j_))) {
                break;
            }
            j_++;
        }
        if (j_ >= _len) {
            return -1;
        }
        return j_;
    }
    public static boolean isOper(String _op) {
        if(StringList.quickEq(_op, "+")) {
            return true;
        }
        if(StringList.quickEq(_op, "-")) {
            return true;
        }
        if(StringList.quickEq(_op, "!")) {
            return true;
        }
        if(StringList.quickEq(_op, "*")) {
            return true;
        }
        if(StringList.quickEq(_op, "/")) {
            return true;
        }
        if(StringList.quickEq(_op, "%")) {
            return true;
        }
        if(StringList.quickEq(_op, "==")) {
            return true;
        }
        if(StringList.quickEq(_op, "!=")) {
            return true;
        }
        if(StringList.quickEq(_op, "<=")) {
            return true;
        }
        if(StringList.quickEq(_op, ">")) {
            return true;
        }
        if(StringList.quickEq(_op, ">=")) {
            return true;
        }
        if(StringList.quickEq(_op, "<")) {
            return true;
        }
        if(StringList.quickEq(_op, "&")) {
            return true;
        }
        if(StringList.quickEq(_op, "|")) {
            return true;
        }
        if(StringList.quickEq(_op, "^")) {
            return true;
        }
        if(StringList.quickEq(_op, "~")) {
            return true;
        }
        if(StringList.quickEq(_op, "<<")) {
            return true;
        }
        if(StringList.quickEq(_op, ">>")) {
            return true;
        }
        if(StringList.quickEq(_op, "<<<")) {
            return true;
        }
        if(StringList.quickEq(_op, ">>>")) {
            return true;
        }
        if(StringList.quickEq(_op, "<<<<")) {
            return true;
        }
        if(StringList.quickEq(_op, ">>>>")) {
            return true;
        }
        if(StringList.quickEq(_op, "++")) {
            return true;
        }
        return StringList.quickEq(_op, "--");
    }

    public static boolean startsWithKeyWord(String _found, String _keyWord) {
        return startsWithKeyWord(_found,0,_keyWord);
    }

    public static boolean startsWithKeyWord(String _found, int _start, String _keyWord) {
        if (!_found.startsWith(_keyWord,_start)) {
            return false;
        }
        if (_found.length() == _keyWord.length()+_start) {
            return true;
        }
        char first_ = _found.charAt(_keyWord.length()+_start);
        return !StringList.isDollarWordChar(first_);
    }
    public static boolean isVar(String _string) {
        String tr_ = _string.trim();
        if (!tr_.startsWith(Templates.PREFIX_VAR_TYPE)) {
            return false;
        }
        tr_ = tr_.substring(Templates.PREFIX_VAR_TYPE.length());
        return isTypeLeaf(tr_);
    }
    public static boolean isTypeLeaf(String _string) {
        if (_string.trim().isEmpty()) {
            return false;
        }
        for (String p : StringList.splitChars(_string, Templates.SEP_CLASS_CHAR)) {
            if (!isTypeLeafPart(p.trim())) {
                return false;
            }
        }
        return true;
    }
    public static boolean isTypeLeafPart(String _string) {
        if (_string.trim().isEmpty()) {
            return false;
        }
        for (char c: _string.toCharArray()) {
            if (!isTypeLeafChar(c)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isTypeLeafChar(char _ch) {
        if (StringList.isDollarWordChar(_ch)) {
            return true;
        }
        if (_ch == Templates.PREFIX_VAR_TYPE_CHAR) {
            return true;
        }
        return false;
    }

    public static String removeDottedSpaces(String _type) {
        StringBuilder b_ = new StringBuilder();
        for (String q: StringList.splitCharSep(_type, Templates.SEP_CLASS_CHAR)) {
            b_.append(q.trim());
        }
        return b_.toString();
    }
}
