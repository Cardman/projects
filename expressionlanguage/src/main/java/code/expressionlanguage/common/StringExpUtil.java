package code.expressionlanguage.common;

import code.expressionlanguage.inherits.Templates;
import code.util.StringList;

public final class StringExpUtil {
    private StringExpUtil() {
    }

    public static boolean nextCharIs(String _str, int _i, int _len, char _value) {
        if (_i >= _len) {
            return false;
        }
        return _str.charAt(_i) == _value;
    }
    public static boolean isOper(String _op) {
        if(StringList.quickEq(_op, "+")) {
            return true;
        }
        if(StringList.quickEq(_op, "-")) {
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
            if (StringList.isDollarWordChar(c)) {
                continue;
            }
            if (c == Templates.PREFIX_VAR_TYPE_CHAR) {
                continue;
            }
            return false;
        }
        return true;
    }
}
