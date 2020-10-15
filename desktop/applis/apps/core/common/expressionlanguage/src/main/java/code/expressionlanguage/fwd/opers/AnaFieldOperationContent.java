package code.expressionlanguage.fwd.opers;

public final class AnaFieldOperationContent {

    private boolean intermediate;

    private final int off;

    public AnaFieldOperationContent(int _off) {
        this.off = _off;
    }

    public int getOff() {
        return off;
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public void setIntermediate(boolean _intermediate) {
        this.intermediate = _intermediate;
    }
}
