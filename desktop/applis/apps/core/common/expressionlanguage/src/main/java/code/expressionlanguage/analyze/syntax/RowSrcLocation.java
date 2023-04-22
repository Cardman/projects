package code.expressionlanguage.analyze.syntax;

public final class RowSrcLocation {
    private final EnSrcLocation kind;
    private final String display;
    private final String fileName;
    private final int index;

    public RowSrcLocation(EnSrcLocation _k,String _d, String _f, int _i) {
        this.kind = _k;
        this.display = _d;
        this.fileName = _f;
        this.index = _i;
    }

    public EnSrcLocation getKind() {
        return kind;
    }

    public String getFileName() {
        return fileName;
    }

    public int getIndex() {
        return index;
    }

    public String getDisplay() {
        return display;
    }
}
