package code.expressionlanguage.analyze.syntax;

public abstract class AbsSrcFileLocationType extends AbsSrcFileLocation {
    private final int offset;

    protected AbsSrcFileLocationType(int _o) {
        this.offset = _o;
    }

    public int getOffset() {
        return offset;
    }
}
