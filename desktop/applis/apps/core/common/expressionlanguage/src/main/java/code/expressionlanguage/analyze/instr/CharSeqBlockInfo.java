package code.expressionlanguage.analyze.instr;


public abstract class CharSeqBlockInfo {
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
}
