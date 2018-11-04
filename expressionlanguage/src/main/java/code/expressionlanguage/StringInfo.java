package code.expressionlanguage;

import code.util.CharList;

public final class StringInfo {

    private CharList chars = new CharList();
    private char[] builtUnicode = new char[4];
    private int indexUnicode;

    public CharList getChars() {
        return chars;
    }

    public void setChars(CharList _chars) {
        chars = _chars;
    }

    public char[] getBuiltUnicode() {
        return builtUnicode;
    }

    public int getIndexUnicode() {
        return indexUnicode;
    }

    public void setIndexUnicode(int _indexUnicode) {
        indexUnicode = _indexUnicode;
    }

}
