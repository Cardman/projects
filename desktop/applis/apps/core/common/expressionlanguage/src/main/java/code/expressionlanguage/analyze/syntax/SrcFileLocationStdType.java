package code.expressionlanguage.analyze.syntax;

public final class SrcFileLocationStdType implements SrcFileLocation {
    private final String type;

    public SrcFileLocationStdType(String _t) {
        this.type = _t;
    }

    @Override
    public String getFileName() {
        return "";
    }

    @Override
    public int getIndex() {
        return 0;
    }

    public String getType() {
        return type;
    }
}
