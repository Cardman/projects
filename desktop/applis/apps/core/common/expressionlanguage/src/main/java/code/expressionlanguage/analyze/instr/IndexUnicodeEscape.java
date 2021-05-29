package code.expressionlanguage.analyze.instr;

public final class IndexUnicodeEscape {

    private int index;

    private boolean escape;

    private int unicode;

    private int nbChars;

    private boolean part;

    private StringInfo stringInfo;

    private TextBlockInfo textInfo;

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public boolean isEscape() {
        return escape;
    }

    public void setEscape(boolean _escape) {
        escape = _escape;
    }

    public int getUnicode() {
        return unicode;
    }

    public void setUnicode(int _unicode) {
        unicode = _unicode;
    }

    public int getNbChars() {
        return nbChars;
    }

    public void setNbChars(int _nbChars) {
        nbChars = _nbChars;
    }

    public boolean isPart() {
        return part;
    }

    public void setPart(boolean _part) {
        part = _part;
    }
    public StringInfo getStringInfo() {
        return stringInfo;
    }
    public void setStringInfo(StringInfo _stringInfo) {
        stringInfo = _stringInfo;
    }

    public TextBlockInfo getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(TextBlockInfo _textInfo) {
        this.textInfo = _textInfo;
    }
}
