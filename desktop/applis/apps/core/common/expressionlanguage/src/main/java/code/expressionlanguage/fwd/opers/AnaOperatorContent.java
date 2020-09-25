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

    public void setOper(String oper) {
        this.oper = oper;
    }

    public void setOpOffset(int opOffset) {
        this.opOffset = opOffset;
    }
}
