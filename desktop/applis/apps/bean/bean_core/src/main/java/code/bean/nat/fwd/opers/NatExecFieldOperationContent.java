package code.bean.nat.fwd.opers;

public final class NatExecFieldOperationContent {

    private final boolean intermediate;

    private final int off;

    public NatExecFieldOperationContent(NatAnaFieldOperationContent _cont) {
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
