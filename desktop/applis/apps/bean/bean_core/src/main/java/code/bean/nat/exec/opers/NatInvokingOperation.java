package code.bean.nat.exec.opers;

public abstract class NatInvokingOperation extends NatExecMethodOperation implements NatExecPossibleIntermediateDotted {
    private final boolean intermediate;

    protected NatInvokingOperation(
            int _o, boolean _intermediateDottedOperation) {
        super(_o);
        intermediate = _intermediateDottedOperation;
    }

    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
