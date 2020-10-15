package code.expressionlanguage.fwd.opers;

public final class AnaOperatorContent {
    private String oper;
    private int opOffset;

    public int getOpOffset() {
        return opOffset;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String _oper) {
        this.oper = _oper;
    }

    public void setOpOffset(int _opOffset) {
        this.opOffset = _opOffset;
    }
}
