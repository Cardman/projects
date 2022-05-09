package code.expressionlanguage.analyze.instr;

public final class TextBlockInfo {
    private boolean line;
    private int lastSpace;
    private boolean printable;
    private final StringBuilder chars = new StringBuilder();
    private String found;
    private boolean ko;
    private final char[] builtUnicode = new char[4];

    public int length() {
        return chars.length();
    }
    public void remove(int _index){
        chars.deleteCharAt(_index);
    }
    public void appendChar(char _char) {
        chars.append(_char);
    }

    public String build() {
        return chars.toString();
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
