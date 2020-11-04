package code.expressionlanguage.exec.stacks;


import code.expressionlanguage.exec.blocks.MethodCallingFinally;

public final class AbruptCallingFinally {
    private final MethodCallingFinally callingFinally;

    public AbruptCallingFinally(MethodCallingFinally _callingFinally) {
        callingFinally = _callingFinally;
    }

    public MethodCallingFinally getCallingFinally() {
        return callingFinally;
    }
}
