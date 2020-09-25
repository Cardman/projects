package code.expressionlanguage.fwd.opers;

public final class ExecFieldOperationContent {

    private final boolean intermediate;

    private final int off;

    public ExecFieldOperationContent(AnaFieldOperationContent _cont) {
        this.intermediate = _cont.isIntermediate();
        this.off = _cont.getOff();
    }

    public int getOff() {
        return off;
    }

    public boolean isIntermediate() {
        return intermediate;
    }
}
