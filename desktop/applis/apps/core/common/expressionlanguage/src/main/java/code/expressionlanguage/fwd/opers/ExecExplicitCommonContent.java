package code.expressionlanguage.fwd.opers;

public final class ExecExplicitCommonContent {
    private final String className;
    private final int offset;

    public ExecExplicitCommonContent(AnaExplicitContent _cont) {
        className = _cont.getClassName();
        offset = _cont.getOffset();
    }

    public int getOffset() {
        return offset;
    }

    public String getClassName() {
        return className;
    }
}
