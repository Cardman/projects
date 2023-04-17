package code.expressionlanguage.analyze.syntax;

public final class SrcFileLocationVariable implements SrcFileLocation {
    private final int deep;
    private final String name;
    private final String fileName;
    private final int index;

    public SrcFileLocationVariable(int _d,String _n,String _f, int _i) {
        deep = _d;
        name = _n;
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

    public int getDeep() {
        return deep;
    }

    public String getName() {
        return name;
    }
}
