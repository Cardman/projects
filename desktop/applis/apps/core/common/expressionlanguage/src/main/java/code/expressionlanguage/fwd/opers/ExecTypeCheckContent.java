package code.expressionlanguage.fwd.opers;

public final class ExecTypeCheckContent {

    private final String className;
    private final int offset;
    public ExecTypeCheckContent(AnaTypeCheckContent _cont) {
        className = _cont.getClassName();
        offset = _cont.getOffset();
    }

    public String getClassName() {
        return className;
    }

    public int getOffset() {
        return offset;
    }
}
