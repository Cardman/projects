package code.bean.nat.fwd.opers;

public final class NatExecFieldOperationContent {

    private final boolean intermediate;

    public NatExecFieldOperationContent(NatAnaFieldOperationContent _cont) {
        this.intermediate = _cont.isIntermediate();
    }

    public boolean isIntermediate() {
        return intermediate;
    }
}
