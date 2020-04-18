package code.expressionlanguage.common;

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
}
