package code.expressionlanguage.analyze.syntax;

public final class SrcFileLocationTypeVar implements SrcFileLocation {
    private final String name;
    private final int index;
    private final String block;

    public SrcFileLocationTypeVar(String _n, int _i, String _fileName) {
        this.name = _n;
        this.index = _i;
        this.block = _fileName;
    }

    @Override
    public String getFileName() {
        return block;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

}
