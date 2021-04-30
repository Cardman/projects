package code.expressionlanguage.common;

import code.util.CharList;

public abstract class CharSeqBlockInfo {
    private final CharList chars = new CharList();
    private String found;
    private boolean ko;
    private final char[] builtUnicode = new char[4];


    public CharList getChars() {
        return chars;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String _found) {
        found = _found;
    }

    public char[] getBuiltUnicode() {
        return builtUnicode;
    }

    public boolean isKo() {
        return ko;
    }

    public void setKo() {
        ko = true;
    }
}
