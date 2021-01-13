package code.expressionlanguage.common;

import code.util.CharList;

public final class TextBlockInfo {

    private final CharList chars = new CharList();
    private String found;
    private boolean ko;
    private boolean line;
    private final char[] builtUnicode = new char[4];
    private int lastSpace;
    private boolean printable;


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

    public boolean isLine() {
        return line;
    }

    public void setLine(boolean _line) {
        line = _line;
    }

    public int getLastSpace() {
        return lastSpace;
    }

    public void setLastSpace(int _lastSpace) {
        this.lastSpace = _lastSpace;
    }

    public boolean isPrintable() {
        return printable;
    }

    public void setPrintable(boolean _printable) {
        this.printable = _printable;
    }
}
