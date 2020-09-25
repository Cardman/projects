package code.expressionlanguage.fwd.opers;

public final class AnaFieldOperationContent {

    private boolean intermediate;

    private final int off;

    public AnaFieldOperationContent(int off) {
        this.off = off;
    }

    public int getOff() {
        return off;
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public void setIntermediate(boolean intermediate) {
        this.intermediate = intermediate;
    }
}
