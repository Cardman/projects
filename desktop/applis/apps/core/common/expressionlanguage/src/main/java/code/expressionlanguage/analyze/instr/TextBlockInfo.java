package code.expressionlanguage.analyze.instr;

public final class TextBlockInfo extends CharSeqBlockInfo {
    private boolean line;
    private int lastSpace;
    private boolean printable;

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
