package code.expressionlanguage.instr;

import code.util.CharList;

public final class StringInfo {

    private CharList chars = new CharList();
    private char[] builtUnicode = new char[4];


    public CharList getChars() {
        return chars;
    }

    public char[] getBuiltUnicode() {
        return builtUnicode;
    }

}
