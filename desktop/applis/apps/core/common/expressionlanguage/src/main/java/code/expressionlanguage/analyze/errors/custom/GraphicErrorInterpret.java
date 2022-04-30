package code.expressionlanguage.analyze.errors.custom;

public final class GraphicErrorInterpret {

    private final int indexFile;

    private int length;

    private final String builtError;

    public GraphicErrorInterpret(FoundErrorInterpret _info, int _indexErr){
        indexFile = _indexErr;
        builtError = _info.getBuiltError();
    }

    public int getIndexFile() {
        return indexFile;
    }

    public String getBuiltError() {
        return builtError;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int _length) {
        length = _length;
    }
}
