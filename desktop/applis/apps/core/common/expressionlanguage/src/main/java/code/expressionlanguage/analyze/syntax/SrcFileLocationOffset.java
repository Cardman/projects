package code.expressionlanguage.analyze.syntax;

public final class SrcFileLocationOffset implements SrcFileLocation {
    private final String fileName;
    private final int index;

    public SrcFileLocationOffset(String _f, int _i) {
        this.fileName = _f;
        index = _i;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public int getIndex() {
        return index;
    }
}
