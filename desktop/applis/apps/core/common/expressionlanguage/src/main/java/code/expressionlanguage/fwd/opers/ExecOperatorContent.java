package code.expressionlanguage.fwd.opers;

public final class ExecOperatorContent {
    private final String oper;
    private final int opOffset;

    public ExecOperatorContent(AnaOperatorContent _cont) {
        oper = _cont.getOper();
        opOffset = _cont.getOpOffset();
    }
    public int getOpOffset() {
        return opOffset;
    }

    public String getOper() {
        return oper;
    }
}
