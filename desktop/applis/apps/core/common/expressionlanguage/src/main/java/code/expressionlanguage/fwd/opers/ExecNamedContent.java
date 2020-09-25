package code.expressionlanguage.fwd.opers;

public final class ExecNamedContent {
    private final int offset;
    private int index;

    public ExecNamedContent(AnaNamedContent _cont) {
        offset = _cont.getOffset();
        index = _cont.getIndex();
    }
    public int getOffset() {
        return offset;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
