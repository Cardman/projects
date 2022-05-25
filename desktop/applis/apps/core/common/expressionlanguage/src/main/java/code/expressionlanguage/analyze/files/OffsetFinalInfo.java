package code.expressionlanguage.analyze.files;

public final class OffsetFinalInfo {
    private final OffsetBooleanInfo finalInfo;
    private final boolean refVariable;

    public OffsetFinalInfo(OffsetBooleanInfo _inf, boolean _ref) {
        this.finalInfo = _inf;
        this.refVariable = _ref;
    }

    public OffsetBooleanInfo getFinalInfo() {
        return finalInfo;
    }

    public boolean isRefVariable() {
        return refVariable;
    }
}
